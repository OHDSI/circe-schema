# Adding CDM Extensions

CIRCE schema models the core OMOP CDM tables out of the box (condition, drug,
procedure, visit, measurement, etc.). Many OMOP implementations extend the CDM with
custom tables — waveforms, genomics, imaging, surveys, etc. This document describes
how to extend the schema, Python, and Java implementations with new criteria types.

## How extensions work

The schema is decomposed into independent files. Each domain criteria type lives in
`spec/criteria/*.schema.json` and extends the base `Criteria` type via `allOf`.
An extension adds a new file that `$ref`s into the same base.

At the code level, a central **registry** maps JSON type names to language-specific
classes. The 16 built-in types register themselves via the same mechanism that
extensions use — there is no privileged path.

| Language | Registration mechanism | When registration happens |
|----------|----------------------|---------------------------|
| Python   | `@register_criteria` decorator | Class definition time (import) |
| Java     | `CohortExpression.registerCriteriaSubtype()` | Typically in a `static {}` block |

---

## Schema layer

Place your extension schema in `spec/extensions/` (or any location of your
choice). Reference the base and common types:

```json
{
  "$schema": "https://json-schema.org/draft/2020-12/schema",
  "$id": "https://github.com/OHDSI/circe-schema/spec/extensions/waveform.schema.json",
  "title": "Waveform CDM Extension Schema",
  "$defs": {
    "WaveformCriteria": {
      "allOf": [
        { "$ref": "../criteria/base.schema.json#/$defs/Criteria" },
        {
          "properties": {
            "CodesetId": { "anyOf": [{ "type": "integer" }, { "type": "null" }] },
            "Channel":    { "anyOf": [{ "type": "string" }, { "type": "null" }] },
            "SamplingRate": {
              "anyOf": [
                { "$ref": "../common.schema.json#/$defs/NumericRange" },
                { "type": "null" }
              ]
            }
          },
          "type": "object"
        }
      ]
    }
  }
}
```

Key points:
- `allOf` with `$ref` to `base.schema.json` inherits `DateAdjustment` and `CorrelatedCriteria`
- `$ref` to `common.schema.json` reuses existing types (`NumericRange`, `DateRange`, etc.)
- The new file is independently validatable and versionable

---

## Python: decorator-based registration

### 1. Define your class

Create a Python package (or module) for your extension:

```python
# circe_schema_waveform/criteria.py
from typing import Optional

from pydantic import AliasChoices, Field

from circe_schema.criteria import Criteria
from circe_schema.core import NumericRange
from circe_schema.criteria_registry import register_criteria


@register_criteria("WaveformCriteria")
class WaveformCriteria(Criteria):
    """Waveform signal criteria for CDM extension."""

    codeset_id: Optional[int] = Field(
        default=None,
        validation_alias=AliasChoices("CodesetId", "codesetId"),
        serialization_alias="CodesetId",
    )
    channel: Optional[str] = Field(
        default=None,
        validation_alias=AliasChoices("Channel", "channel"),
        serialization_alias="Channel",
    )
    sampling_rate: Optional[NumericRange] = Field(
        default=None,
        validation_alias=AliasChoices("SamplingRate", "samplingRate"),
        serialization_alias="SamplingRate",
    )
```

### 2. Import your module

The `@register_criteria` decorator fires at class-definition time. You must import
your module before deserialization:

```python
import circe_schema_waveform  # registers WaveformCriteria
from circe_schema import CohortExpression

with open("expression.json") as f:
    cohort = CohortExpression.model_validate_json(f.read())
# cohort.primaryCriteria.criteriaList now handles WaveformCriteria
```

Or use `import circe_schema_waveform.criteria` if your package doesn't auto-import
the module.

### 3. Wire format

Any `CriteriaList`, `CensoringCriteria`, or `CorrelatedCriteria` item can now be
a wrapped `WaveformCriteria`:

```json
{
  "ConditionOccurrence": { "CodesetId": 1 },
  "WaveformCriteria": { "CodesetId": 2, "Channel": "ECG_LEAD_II" }
}
```

### Verification

```python
from circe_schema.criteria_registry import get_all_criteria

print(get_all_criteria().keys())
# dict_keys(['ConditionOccurrence', 'DrugExposure', ..., 'WaveformCriteria'])
```

---

## Java: compile-time extension with programmatic registration

### 1. Set up your Maven project

```xml
<dependencies>
    <dependency>
        <groupId>org.ohdsi.circe</groupId>
        <artifactId>circe-schema</artifactId>
        <version>1.0.0</version>
    </dependency>
</dependencies>
```

### 2. Define your class

```java
package com.example.waveform;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.ohdsi.circe.cohortdefinition.CohortExpression;
import org.ohdsi.circe.cohortdefinition.Criteria;
import org.ohdsi.circe.cohortdefinition.NumericRange;

@JsonTypeName("WaveformCriteria")
public class WaveformCriteria extends Criteria {

    @JsonProperty("CodesetId")
    public Integer codesetId;

    @JsonProperty("Channel")
    public String channel;

    @JsonProperty("SamplingRate")
    public NumericRange samplingRate;

    static {
        CohortExpression.registerCriteriaSubtype(WaveformCriteria.class);
    }
}
```

The `@JsonTypeName("WaveformCriteria")` annotation tells Jackson what wrapper key
to use. The `static {}` block registers the class with both Jackson's subtype
resolver and the `CriteriaRegistry` for programmatic lookup.

### 3. Deserialize

After your class is loaded (which happens when the JVM processes the `static {}`
block), `CohortExpression.fromJson()` automatically handles the new type:

```java
import com.example.waveform.WaveformCriteria;
// Class loading triggers the static block → registration

CohortExpression cohort = CohortExpression.fromJson(json);
for (Criteria c : cohort.primaryCriteria.criteriaList) {
    if (c instanceof WaveformCriteria) {
        WaveformCriteria wc = (WaveformCriteria) c;
        System.out.println("Channel: " + wc.channel);
    }
}
```

### 4. Programmatic lookup

You can also query the registry directly:

```java
import org.ohdsi.circe.cohortdefinition.CriteriaRegistry;

CriteriaRegistry.get("WaveformCriteria");  // → WaveformCriteria.class
CriteriaRegistry.isRegistered("ConditionOccurrence");  // → true
CriteriaRegistry.getAll();  // all 16 built-in + all extension types
```

---

## Lifecycle summary

| Step | Python | Java |
|------|--------|------|
| Schema | `.schema.json` referencing `base.schema.json` | Same |
| Class | Extends `Criteria` + `@register_criteria` decorator | Extends `Criteria` + `@JsonTypeName` |
| Registration | Decorator fires at class definition time | `CohortExpression.registerCriteriaSubtype()` in `static {}` |
| Deserialization | `CohortExpression.model_validate_json()` | `CohortExpression.fromJson()` |
| Dispatch | `isinstance(item, WaveformCriteria)` | `item instanceof WaveformCriteria` |

---

## Design rationale

**Why a decorator / programmatic registration instead of auto-discovery?**
Extensions are separate packages compiled against circe-schema at build time.
They control their own initialization. A decorator (Python) or `static {}` block
(Java) is explicit, testable, and requires no classpath scanning or
configuration files.

**Why no privileged path for built-in types?**
The 16 built-in criteria types use the exact same registration mechanism as
extensions — `@register_criteria` in Python, and the `@JsonTypeName` +
`CohortExpression.registerCriteriaSubtype()` path in Java. Removing an
extension is simply removing its package from the classpath.

**Thread safety (Java):**
`CohortExpression.registerCriteriaSubtype()` calls
`ObjectMapper.registerSubtypes()` which is thread-safe in Jackson 2.x.
`CriteriaRegistry` uses a `LinkedHashMap` — register at initialization time,
not during request handling.
