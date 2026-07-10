# CIRCE Schema

** UNDER DRAFT/DEVELOPMENT ** code and schema definitions in this repository and not currently in a usable state.

Language-agnostic schema for OMOP CDM cohort definitions.

This repository defines the **data model** for CIRCE cohort expressions — the standard used by [OHDSI Circe-be (Java)](https://github.com/OHDSI/circe-be) and [OHDSI CircePy (Python)](https://github.com/OHDSI/CircePy) for representing cohort definitions in the OMOP Common Data Model.

## What this is

- A **single source of truth** (`spec/cohort-expression.schema.json`) defining the cohort expression data model
- **Language-specific implementations** of that model (Python, Java, with R and TypeScript planned)
- **Shared test fixtures** (`fixtures/`) used to verify cross-language conformance

## What this is NOT

- Not a SQL generation engine (see Circe-be and CircePy for that)
- Not a validation framework
- Not tied to any specific OMOP CDM version
- A usable implementation. This only exists to move towards a shared conformity across cohort definitions within ohdsi
 
## Repository structure

```
circe-schema/
├── spec/                           # JSON Schema specification
│   └── cohort-expression.schema.json
├── fixtures/                       # Cross-language test fixtures
│   ├── minimal-cohort.json
│   ├── multi-criteria-cohort.json
│   ├── with-inclusion-rules.json
│   ├── with-censoring.json
│   ├── with-windowed-criteria.json
│   └── with-custom-era.json
├── python/                         # Python implementation (Pydantic)
├── java/                           # Java implementation (Jackson)
├── r/                              # R implementation (R6) — coming soon
└── typescript/                     # TypeScript implementation — coming soon
```

## Implementations

### Python

```bash
cd python
pip install -e ".[dev]"
pytest tests/
```

The Python package provides Pydantic v2 models that serialize to/from the JSON Schema format. Supports both Python `snake_case` and Java `camelCase`/`PascalCase` field names.

### Java

```bash
cd java
mvn test
```

The Java package provides Jackson-annotated POJOs. Uses `@JsonTypeInfo(As.WRAPPER_OBJECT)` for polymorphic criteria and end strategy serialization.

## The data model

A `CohortExpression` consists of:

| Component | Description |
|-----------|-------------|
| `PrimaryCriteria` | Domain-specific criteria defining cohort entry events |
| `AdditionalCriteria` | Criteria that all entry events must satisfy |
| `InclusionRules` | Rules determining which qualifying events become cohort records |
| `EndStrategy` | How cohort episode end dates are determined (DateOffset or CustomEra) |
| `CensoringCriteria` | Criteria that can end a cohort episode early |
| `ConceptSets` | OMOP concept sets referenced by criteria |
| `CollapseSettings` | How overlapping episodes are collapsed |

### Criteria types (16 domain types)

ConditionOccurrence, DrugExposure, ProcedureOccurrence, VisitOccurrence, Observation, Measurement, DeviceExposure, Specimen, Death, VisitDetail, ObservationPeriod, PayerPlanPeriod, LocationRegion, ConditionEra, DrugEra, DoseEra

Each criteria is serialized with a polymorphic wrapper: `{"ConditionOccurrence": {...}}`

### End strategy types (2 types)

- `DateOffset`: Fixed offset from a date field
- `CustomEra`: Drug-era-based persistence

Each end strategy is serialized with a polymorphic wrapper: `{"DateOffset": {...}}` or `{"CustomEra": {...}}`

## Conformance

All implementations must:
1. Parse all fixture JSON files without error
2. Serialize model objects to valid JSON
3. Round-trip (parse → serialize → parse) without data loss

## License

Apache 2.0
