# CIRCE Cohort Definition Standard Structure (DRAFT)

A comprehensive guide to the Pydantic model hierarchy for the CIRCE (Cohort Inclusion and Restriction Criteria Engine) Python implementation.

---

## How to Read These Diagrams

Every diagram uses two visual dimensions:

1. **Node color = the role a model plays** in the cohort definition.
2. **Field box fill = whether an attribute is required**, for boxes that represent individual fields.

The swatches below render in the exact colors used throughout this document:

```mermaid
graph LR
    subgraph Model_Roles["Model roles (box color)"]
        direction TB
        ROOT["Root model<br/>top-level entry point<br/>CohortExpression, ConceptSet"]
        CONT["Container model<br/>groups / nests other models<br/>PrimaryCriteria, InclusionRule,<br/>CriteriaGroup, EndStrategy"]
        EXPR["Event / criteria model<br/>matches clinical events<br/>ConditionOccurrence, DrugExposure,<br/>WindowedCriteria, ConceptExpressionItem"]
        DET["Building-block model<br/>reusable value structures<br/>Window, DateRange, Occurrence,<br/>Concept, ResultLimit, CollapseSettings"]
    end

    subgraph Field_Boxes["Field boxes (fill)"]
        direction TB
        REQ["Required field<br/>marked with a trailing *"]
        OPT["Optional field"]
    end

    classDef root fill:#0277bd,stroke:#01579b,stroke-width:3px,color:#fff
    classDef container fill:#9c27b0,stroke:#6a1b9a,stroke-width:3px,color:#fff
    classDef expression fill:#ff9800,stroke:#e65100,stroke-width:2px,color:#fff
    classDef detail fill:#2e7d32,stroke:#1b5e20,stroke-width:2px,color:#fff
    classDef required fill:#ffd54f,stroke:#ff6f00,stroke-width:4px,color:#000
    classDef optional fill:#fff,stroke:#999,stroke-width:2px,color:#333

    class ROOT root
    class CONT container
    class EXPR expression
    class DET detail
    class REQ required
    class OPT optional
```

| Swatch | Meaning |
|--------|---------|
| **Blue** | **Root model** — the top-level object that anchors a diagram and owns everything beneath it (`CohortExpression`, and `ConceptSet` within its own subtree). |
| **Purple** | **Container model** — structural classes whose job is to group or recursively nest other models (`PrimaryCriteria`, `InclusionRule`, `CriteriaGroup`, `ConceptSetExpression`, `EndStrategy`). |
| **Orange** | **Event / criteria model** — classes that describe clinical events to match, plus their time-window and occurrence wrappers (`ConditionOccurrence`, `DrugExposure`, the other 14 criteria types, `WindowedCriteria`, `CorelatedCriteria`, `ConceptExpressionItem`). |
| **Green** | **Building-block model** — small, reusable value structures and leaf details shared across many models (`Window`, `WindowBound`, `DateRange`, `NumericRange`, `Occurrence`, `Concept`, `ResultLimit`, `CollapseSettings`, and the abstract base classes). |
| **Yellow fill, thick orange border** | **Required field** — an attribute that must be present; these are also flagged with a trailing `*` in the box label. |
| **White fill, thin gray border** | **Optional field** — an attribute that may be omitted. |

---

## 1. Root Level: CohortExpression

**Purpose:** The root container and entry point for entire cohort definitions. `CohortExpression` encapsulates all aspects of a cohort—from the initial population selection (primary criteria) to post-index event constraints (additional criteria), inclusion rules, concept sets, and exit strategies.

**Key Responsibilities:**
- Holds all concept sets used in the cohort definition (vocabulary lookups)
- Defines initial patient index events via `primary_criteria`
- Specifies post-index event logic and demographic filters via `additional_criteria`
- Applies additional inclusion/exclusion rules via `inclusion_rules`
- Determines cohort exit dates via `end_strategy`
- Controls event collapsing (era logic) with `collapse_settings`
- Limits result sizes with `expression_limit` and `qualified_limit`

**In Practice:** When exporting a cohort definition to JSON, the entire structure becomes a single `CohortExpression` object, making it portable and self-contained.

```mermaid
graph TD
    A["CohortExpression"]
    B["concept_sets*"]
    C["primary_criteria"]
    D["additional_criteria"]
    E["inclusion_rules"]
    F["censoring_criteria"]
    G["end_strategy"]
    H["collapse_settings"]
    I["expression_limit"]
    J["qualified_limit"]
    K["censor_window"]
    L["title"]
    M["cdm_version_range"]
    
    A --> B
    A --> C
    A --> D
    A --> E
    A --> F
    A --> G
    A --> H
    A --> I
    A --> J
    A --> K
    A --> L
    A --> M
    
    classDef required fill:#ffd54f,stroke:#ff6f00,stroke-width:4px,color:#000
    classDef optional fill:#fff,stroke:#999,stroke-width:2px,color:#333
    classDef root fill:#0277bd,stroke:#01579b,stroke-width:3px,color:#fff
    
    class A root
    class B required
    class C,D,E,F,G,H,I,J,K,L,M optional
```

**Key Fields:**
- `concept_sets` (required): Array of named concept collections
- `primary_criteria`: Initial event criteria for cohort entry
- `additional_criteria`: Post-index event logic with AND/OR constraints
- `inclusion_rules`: Additional inclusion/exclusion filters
- `end_strategy`: Definition of cohort exit criteria
- `collapse_settings`: Era collapsing behavior
- `expression_limit`, `qualified_limit`: Result limiting strategies
- `title`, `cdm_version_range`: Metadata

---

## 2. Primary Criteria & Inclusion Rules

**Purpose:** Define the initial patient population and how to identify index events. This section handles entry into the cohort.

### PrimaryCriteria Structure

**What it does:** Identifies the initial events that mark cohort entry. All patients in the cohort must have at least one event matching the criteria in `criteria_list`. These are domain events (condition occurrences, drug exposures, etc.) that serve as the index date anchor point for the entire cohort.

**Key fields:**
- `criteria_list`: The actual event types to search for (16 possible criteria types)
- `observation_window`: Time window the patient must have been observable (prior and post days)
- `primary_limit`: How many matching index events to keep per patient (First, Last, All)

```mermaid
graph TD
    A["PrimaryCriteria"]
    B["criteria_list*"]
    C["observation_window"]
    D["primary_limit"]
    B1["CriteriaType<br/>16 types"]
    B2["Domain Events 13"]
    B3["Era Criteria 3"]
    B4["Special Criteria"]
    C1["PriorDays"]
    C2["PostDays"]
    D1["Type"]
    D2["Value"]
    
    A --> B
    A --> C
    A --> D
    B --> B1
    B1 --> B2
    B1 --> B3
    B1 --> B4
    C --> C1
    C --> C2
    D --> D1
    D --> D2
    
    classDef container fill:#9c27b0,stroke:#6a1b9a,stroke-width:3px,color:#fff
    classDef required fill:#ffd54f,stroke:#ff6f00,stroke-width:4px,color:#000
    classDef optional fill:#fff,stroke:#999,stroke-width:2px,color:#333
    
    class A container
    class B required
    class C,D,B1,B2,B3,B4,C1,C2,D1,D2 optional
```

### InclusionRule Structure

**What it does:** Defines additional inclusion/exclusion criteria that patients must satisfy *after* the primary criteria matches. Each inclusion rule is named and contains a `CriteriaGroup` with post-index event constraints. A patient must satisfy ALL inclusion rules to remain in the cohort.

**Use case:** After identifying patients with a condition (primary criteria), inclusion rules might require they also have a follow-up visit or exclude patients with certain comorbidities.

```mermaid
graph TD
    A["InclusionRule"]
    B["name"]
    C["description"]
    D["expression"]
    D1["CriteriaGroup"]
    
    A --> B
    A --> C
    A --> D
    D --> D1
    
    classDef container fill:#9c27b0,stroke:#6a1b9a,stroke-width:3px,color:#fff
    classDef optional fill:#fff,stroke:#999,stroke-width:2px,color:#333
    
    class A,D1 container
    class B,C,D optional
```

---

## 3. Criteria Hierarchy

**Purpose:** Define all 16 concrete criteria types that can be used to identify events in observational data. The criteria hierarchy is the backbone of the cohort definition system.

### Base Criteria Classes

**The hierarchy:**
- `Criteria` (base): All domain and era-based criteria inherit from this class
- `WindowedCriteria`: Adds time window constraints (for post-index events)
- `CorelatedCriteria`: Extends `WindowedCriteria` with occurrence counting (e.g., "exactly 2 times", "at least once")
- `DemographicCriteria`: Special filters for age, gender, race, ethnicity
- `Occurrence`: Tracks how many times a criteria must occur
- `EndStrategy`: Polymorphic base for exit strategies

```mermaid
graph TD
    A["CirceBaseModel"]
    B["Criteria"]
    C["WindowedCriteria"]
    D["CorelatedCriteria"]
    E["DemographicCriteria"]
    F["Occurrence"]
    G["EndStrategy"]
    B1["16 Concrete Types"]
    B2["Domain Event 13"]
    B3["Era 3"]
    C1["Windowed with<br/>time constraints"]
    D1["Correlated with<br/>occurrence rules"]
    
    A --> B
    A --> C
    A --> D
    A --> E
    A --> F
    A --> G
    B --> B1
    B1 --> B2
    B1 --> B3
    C --> C1
    D --> D1
    
    classDef detail fill:#2e7d32,stroke:#1b5e20,stroke-width:2px,color:#fff
    classDef optional fill:#fff,stroke:#999,stroke-width:2px,color:#333
    
    class A,B,C,D,E,F,G detail
    class B1,B2,B3,C1,D1 optional
```

### Criteria Types: All Categories

**The 16 concrete types are organized into three categories:**

1. **Domain Event Criteria (13)**: Represent clinical events from the OMOP common data model
   - Event tables: ConditionOccurrence, DrugExposure, ProcedureOccurrence, VisitOccurrence, VisitDetail, Observation, Measurement, DeviceExposure, Specimen, Death
   - Period tables: ObservationPeriod, PayerPlanPeriod, LocationRegion

2. **Era Criteria (3)**: Represent continuous periods derived from events
   - ConditionEra: Continuous periods with no gap in condition diagnoses
   - DrugEra: Continuous periods with no gap in drug exposure
   - DoseEra: Continuous periods at a specific dose level

3. **Geographic Criteria (1)**: Special criteria for location-based queries
   - GeoCriteria: Region/location constraints

```mermaid
graph TD
    Base["Criteria Base"]
    
    D1["ConditionOccurrence"]
    D2["DrugExposure"]
    D3["ProcedureOccurrence"]
    D4["VisitOccurrence"]
    D5["VisitDetail"]
    D6["Observation"]
    D7["Measurement"]
    D8["DeviceExposure"]
    D9["Specimen"]
    D10["Death"]
    D11["ObservationPeriod"]
    D12["PayerPlanPeriod"]
    D13["LocationRegion"]
    
    E1["ConditionEra"]
    E2["DrugEra"]
    E3["DoseEra"]
    
    G1["GeoCriteria"]
    
    Base --> D1
    Base --> D2
    Base --> D3
    Base --> D4
    Base --> D5
    Base --> D6
    Base --> D7
    Base --> D8
    Base --> D9
    Base --> D10
    Base --> D11
    Base --> D12
    Base --> D13
    Base --> E1
    Base --> E2
    Base --> E3
    Base --> G1
    
    classDef detail fill:#2e7d32,stroke:#1b5e20,stroke-width:2px,color:#fff
    classDef expression fill:#ff9800,stroke:#e65100,stroke-width:2px,color:#fff
    
    class Base detail
    class D1,D2,D3,D4,D5,D6,D7,D8,D9,D10,D11,D12,D13,E1,E2,E3,G1 expression
```

---

## 4. Criteria Group: Post-Index Logic

**Purpose:** Define complex boolean logic for post-index event constraints. This is the most powerful component for building sophisticated cohort logic.

**Key concept:** `CriteriaGroup` is *recursive*—it can contain other `CriteriaGroup`s, enabling arbitrarily complex nested AND/OR logic with demographic filters and windowed criteria.

**Components:**
- `criteria_list`: Correlated criteria that must/must not occur within specified time windows
- `groups`: Nested `CriteriaGroup`s for recursive boolean logic (AND/OR combinations)
- `demographic_criteria_list`: Age, gender, race filters to apply to all criteria in the group
- `type`: "AND" or "OR" logic operator
- `count`: For AND groups, how many criteria must match (optional, defaults to all)

**In practice:** After identifying patients with a disease (primary criteria), you'd use `CriteriaGroup` to ensure they:
- Have a visit within 30 days AND a lab result within 60 days
- AND are between 18-65 years old
- OR have two separate procedure codes within 1 year

```mermaid
graph TD
    A["CriteriaGroup"]
    B["criteria_list"]
    C["groups"]
    D["demographic_criteria_list"]
    E["type"]
    F["count"]
    
    B1["CorelatedCriteria 0..N"]
    B2["criteria CriteriaType"]
    B3["start_window Window"]
    B4["end_window Window"]
    B5["occurrence Occurrence"]
    
    C1["CriteriaGroup 0..N<br/>RECURSIVE"]
    
    D1["DemographicCriteria 0..N"]
    D2["age, gender, race,<br/>ethnicity"]
    
    E1["AND or OR"]
    
    A --> B
    A --> C
    A --> D
    A --> E
    A --> F
    B --> B1
    B1 --> B2
    B1 --> B3
    B1 --> B4
    B1 --> B5
    C --> C1
    D --> D1
    D1 --> D2
    E --> E1
    
    classDef container fill:#9c27b0,stroke:#6a1b9a,stroke-width:3px,color:#fff
    classDef optional fill:#fff,stroke:#999,stroke-width:2px,color:#333
    
    class A,C1 container
    class B,C,D,E,F,B1,B2,B3,B4,B5,D1,D2,E1 optional
```

**Fields:**
- `criteria_list`: Correlated criteria with time windows
- `groups`: Nested CriteriaGroups for recursive boolean logic
- `demographic_criteria_list`: Age, gender, race filters
- `type`: Logical operator (AND or OR)
- `count`: Number of criteria required (for AND operations)

---

## 5. Windowed & Correlated Criteria

**Purpose:** Add temporal constraints to post-index events. Instead of just "must happen", you can now specify "must happen between 30 and 90 days after the index event, relative to the event end date".

### WindowedCriteria Structure

**What it does:** Wraps a criteria with start and end time windows. Used in post-index event filtering to constrain when events can occur relative to the index date or other reference points.

**Key fields:**
- `criteria`: The actual criteria type to look for
- `start_window`: When this criteria can START occurring (days before/after index)
- `end_window`: When this criteria can END occurring (days before/after index)
- Windows are relative to the index date by default, but can reference event end dates instead

```mermaid
graph TD
    A["WindowedCriteria"]
    B["criteria*"]
    C["start_window"]
    D["end_window"]
    
    B1["CriteriaType<br/>specific criteria"]
    
    C1["Window"]
    D1["Window"]
    
    C2["start WindowBound"]
    C3["end WindowBound"]
    C4["useEventEnd bool"]
    C5["useIndexEnd bool"]
    
    C2a["coeff int<br/>days int"]
    
    A --> B
    A --> C
    A --> D
    B --> B1
    C --> C1
    D --> D1
    C1 --> C2
    C1 --> C3
    C1 --> C4
    C1 --> C5
    C2 --> C2a
    
    classDef expression fill:#ff9800,stroke:#e65100,stroke-width:2px,color:#fff
    classDef required fill:#ffd54f,stroke:#ff6f00,stroke-width:4px,color:#000
    classDef optional fill:#fff,stroke:#999,stroke-width:2px,color:#333
    classDef detail fill:#2e7d32,stroke:#1b5e20,stroke-width:2px,color:#fff
    
    class A expression
    class B required
    class C,D,B1 optional
    class C1,D1 detail
    class C2,C3,C4,C5,C2a optional
```

### CorelatedCriteria Structure

**What it does:** Extends `WindowedCriteria` by adding occurrence counting. This lets you specify "at least 2 occurrences", "exactly 1", or "at most 3" of an event within a time window.

**Example:** "Must have at least 2 separate visits in the 90 days after the index event, counted as distinct visit dates."

```mermaid
graph TD
    A["CorelatedCriteria"]
    B["criteria"]
    C["start_window"]
    D["end_window"]
    E["occurrence"]
    F["restrict_visit"]
    
    E1["Occurrence"]
    E2["type int<br/>0=EXACTLY<br/>1=AT_MOST<br/>2=AT_LEAST"]
    E3["count int"]
    E4["is_distinct bool"]
    E5["count_column<br/>CriteriaColumn"]
    
    A --> B
    A --> C
    A --> D
    A --> E
    A --> F
    E --> E1
    E1 --> E2
    E1 --> E3
    E1 --> E4
    E1 --> E5
    
    classDef expression fill:#ff9800,stroke:#e65100,stroke-width:2px,color:#fff
    classDef optional fill:#fff,stroke:#999,stroke-width:2px,color:#333
    classDef detail fill:#2e7d32,stroke:#1b5e20,stroke-width:2px,color:#fff
    
    class A expression
    class B,C,D,F optional
    class E1 detail
    class E2,E3,E4,E5 optional
```

---

## 6. Supporting Structures

**Purpose:** Provide reusable, granular building blocks for common patterns (time windows, ranges, demographic selections, etc.).

### Window & WindowBound

**What it does:** Defines a time interval with a start and end bound. Used to constrain when post-index events can occur.

**WindowBound fields:**
- `coeff`: Coefficient indicating reference point (1 = start of index event, 0 = end of index event, -1 = day before index)
- `days`: Number of days offset from the reference point

**Example:** `start_window` with `coeff=1, days=30` means "30 days after the start of the index event"

```mermaid
graph TD
    A["Window"]
    B["start"]
    C["end"]
    D["useEventEnd"]
    E["useIndexEnd"]
    
    B1["WindowBound"]
    C1["WindowBound"]
    
    B2["coeff int"]
    B3["days int"]
    
    F["WindowBound coeff<br/>1=Start index<br/>0=End index<br/>-1=Day before"]
    
    A --> B
    A --> C
    A --> D
    A --> E
    B --> B1
    C --> C1
    B1 --> B2
    B1 --> B3
    B --> F
    
    classDef detail fill:#2e7d32,stroke:#1b5e20,stroke-width:2px,color:#fff
    classDef optional fill:#fff,stroke:#999,stroke-width:2px,color:#333
    
    class A detail
    class B,C,D,E,B1,C1,B2,B3,F optional
```

### DateRange & NumericRange

**What they do:** Define comparison operators and value ranges for filtering criteria. Used throughout criteria to specify constraints.

**Common operations:**
- `=`: Equal to value
- `<`, `>`, `<=`, `>=`: Comparison operators
- For ranges: `Op` and `Extent` combined define a BETWEEN operation

**Examples:**
- Age between 18 and 65: `NumericRange` with `Op=">=", Value=18, Extent=65`
- Lab value > 100: `NumericRange` with `Op=">", Value=100`

```mermaid
graph TD
    A["DateRange<br/>or NumericRange"]
    B["op"]
    C["value"]
    D["extent"]
    
    B1["=, lt, gt<br/>lte, gte"]
    C1["Numeric value<br/>or date"]
    D1["Range extent<br/>for BETWEEN"]
    
    A --> B
    A --> C
    A --> D
    B --> B1
    C --> C1
    D --> D1
    
    classDef detail fill:#2e7d32,stroke:#1b5e20,stroke-width:2px,color:#fff
    classDef optional fill:#fff,stroke:#999,stroke-width:2px,color:#333
    
    class A detail
    class B,C,D,B1,C1,D1 optional
```

### DemographicCriteria Structure

**What it does:** Filters events or entire patient populations by demographic attributes. Can be applied at the primary criteria level or within post-index criteria groups.

**Supported filters:**
- Gender: List of gender concept IDs or concept set reference
- Age: Numeric range (e.g., 18-65)
- Race: List of race concepts or concept set reference
- Ethnicity: List of ethnicity concepts or concept set reference
- Occurrence start/end dates: Restrict events to specific calendar date ranges

**Use case:** Restrict a cohort to females aged 40+ who had an event in 2023.

```mermaid
graph TD
    A["DemographicCriteria"]
    B["gender<br/>gender_cs"]
    C["race<br/>race_cs"]
    D["ethnicity<br/>ethnicity_cs"]
    E["age"]
    F["occurrence_start_date"]
    G["occurrence_end_date"]
    
    B1["Concept list<br/>or ConceptSetSelection"]
    E1["NumericRange"]
    F1["DateRange"]
    
    A --> B
    A --> C
    A --> D
    A --> E
    A --> F
    A --> G
    B --> B1
    E --> E1
    F --> F1
    
    classDef detail fill:#2e7d32,stroke:#1b5e20,stroke-width:2px,color:#fff
    classDef optional fill:#fff,stroke:#999,stroke-width:2px,color:#333
    
    class A detail
    class B,C,D,E,F,G,B1,E1,F1 optional
```

### Occurrence Structure

**What it does:** Specifies how many times a criteria must occur. Used in `CorelatedCriteria` to enforce occurrence constraints.

**Type values:**
- `0` (EXACTLY): The criteria must occur exactly N times
- `1` (AT_MOST): The criteria may occur up to N times
- `2` (AT_LEAST): The criteria must occur at least N times

**Count column** (optional): Specify which field to count (e.g., days_supply, quantity, occurrence_count in era tables).

**Example:** "At least 2 distinct days with a drug exposure" vs "at least 2 quantities of a drug."

```mermaid
graph TD
    A["Occurrence"]
    B["type*"]
    C["count*"]
    D["is_distinct"]
    E["count_column"]
    
    B1["0=EXACTLY<br/>1=AT_MOST<br/>2=AT_LEAST"]
    
    E1["Optional<br/>days_supply, quantity<br/>refills, duration etc"]
    
    A --> B
    A --> C
    A --> D
    A --> E
    B --> B1
    E --> E1
    
    classDef required fill:#ffd54f,stroke:#ff6f00,stroke-width:4px,color:#000
    classDef optional fill:#fff,stroke:#999,stroke-width:2px,color:#333
    
    class B,C required
    class A,D,E,B1,E1 optional
```

---

## 7. End Strategy Types

**Purpose:** Define when a patient exits the cohort (end date). Without an explicit strategy, cohort membership is permanent or determined by data availability.

**Two main strategies:**

1. **DateOffsetStrategy**: Fixed exit date relative to index
   - Simple: Exit N days after the index date
   - Use case: 365-day follow-up periods

2. **CustomEraStrategy**: Event-driven exit
   - Exit when a specific condition/drug era ends
   - Use case: Patients on a treatment until it stops

```mermaid
graph TD
    A["EndStrategy"]
    B["DateOffsetStrategy"]
    C["CustomEraStrategy"]
    
    B1["days_offset int"]
    B2["Exit after<br/>fixed days"]
    
    C1["era_type str"]
    C1a["condition_era<br/>or drug_era"]
    C2["codeset_id int"]
    C3["Exit based on<br/>era end date"]
    
    A --> B
    A --> C
    B --> B1
    B --> B2
    C --> C1
    C --> C2
    C --> C3
    C1 --> C1a
    
    classDef container fill:#9c27b0,stroke:#6a1b9a,stroke-width:3px,color:#fff
    classDef optional fill:#fff,stroke:#999,stroke-width:2px,color:#333
    
    class A container
    class B,C,B1,B2,C1,C1a,C2,C3 optional
```

---

## 8. Concept Set & Concept Models

**Purpose:** Define named collections of OMOP vocabulary concepts that can be referenced throughout the cohort definition. Concept sets are reusable and version-controlled.

**Key components:**
- `ConceptSet`: Named container with metadata
- `ConceptSetExpression`: The logic for including/excluding concepts
- `ConceptExpressionItem`: Individual concept with include/exclude flags
- `Concept`: OMOP vocabulary reference with optional metadata

**Concept flags control expansion:**
- `isExcluded`: FALSE = include in set, TRUE = exclude from set
- `includeDescendants`: Automatically include child concepts in vocabulary hierarchy
- `includeMapped`: Include concepts mapped from source vocabularies

**Example:** A "Diabetes" concept set might include:
- Type 1 Diabetes concept + descendants (all subtypes)
- Type 2 Diabetes concept + descendants (all subtypes)
- Gestational Diabetes but EXCLUDE a specific rare subtype

```mermaid
graph TD
    A["ConceptSet"]
    B["id*"]
    C["name*"]
    D["expression*"]
    E["metadata"]
    
    D1["ConceptSetExpression"]
    D2["items*"]
    
    D3["ConceptExpressionItem"]
    D4["concept*"]
    D5["isExcluded*"]
    D6["includeDescendants*"]
    D7["includeMapped*"]
    
    D4a["Concept"]
    D4b["conceptId*"]
    D4c["conceptName"]
    D4d["domainId"]
    
    A --> B
    A --> C
    A --> D
    A --> E
    D --> D1
    D1 --> D2
    D2 --> D3
    D3 --> D4
    D3 --> D5
    D3 --> D6
    D3 --> D7
    D4 --> D4a
    D4a --> D4b
    D4a --> D4c
    D4a --> D4d
    
    classDef required fill:#ffd54f,stroke:#ff6f00,stroke-width:4px,color:#000
    classDef optional fill:#fff,stroke:#999,stroke-width:2px,color:#333
    classDef root fill:#0277bd,stroke:#01579b,stroke-width:3px,color:#fff
    classDef container fill:#9c27b0,stroke:#6a1b9a,stroke-width:3px,color:#fff
    classDef expression fill:#ff9800,stroke:#e65100,stroke-width:2px,color:#fff
    classDef detail fill:#2e7d32,stroke:#1b5e20,stroke-width:2px,color:#fff
    
    class A root
    class B,C,D required
    class E optional
    class D1 container
    class D2 required
    class D3 expression
    class D4,D5,D6,D7 required
    class D4a detail
    class D4b required
    class D4c,D4d optional
```

---

## 9. Result Limiting Strategy

**Purpose:** Control how many results are returned from a cohort query. Useful for testing and resource management.

**Limit types:**
- `First`: Return only the first N patients or events
- `Last`: Return only the last N patients or events  
- `All`: Return all (no limit)

**Used for:**
- Development/testing: Run on first 100 patients to validate logic
- Performance: Limit large cohorts to manageable sizes
- Sampling: Analyze a subset of results

```mermaid
graph TD
    A["ResultLimit"]
    B["type"]
    C["value"]
    
    B1["First or Last<br/>or All"]
    C1["N persons or events"]
    
    A --> B
    A --> C
    B --> B1
    C --> C1
    
    classDef detail fill:#2e7d32,stroke:#1b5e20,stroke-width:2px,color:#fff
    classDef optional fill:#fff,stroke:#999,stroke-width:2px,color:#333
    
    class A detail
    class B,C,B1,C1 optional
```

---

## 10. Collapse Settings

**Purpose:** Control how overlapping or consecutive events are combined. Essential for era-based analyses.

**Collapse types:**
- `ERA`: Combine events into continuous eras with configurable gap days
  - Example: Drug exposures within 30 days become a single era
- `NO_COLLAPSE`: Keep all events separate (no combining)
- `COLLAPSE` (legacy): Alternative collapsing mode

**Era pad parameter:**
- `era_pad`: Days of acceptable gap between events (default: 0)
- Era pad of 30 means: If two events are ≤ 30 days apart, combine them into one era

**Use case:** A patient fills a prescription every 28 days—with `era_pad=30`, these become one continuous drug era instead of 12 separate exposures.

```mermaid
graph TD
    A["CollapseSettings"]
    B["era_pad*"]
    C["collapse_type"]
    
    B1["Number of days"]
    C1["ERA or COLLAPSE<br/>or NO_COLLAPSE"]
    
    A --> B
    A --> C
    B --> B1
    C --> C1
    
    classDef detail fill:#2e7d32,stroke:#1b5e20,stroke-width:2px,color:#fff
    classDef required fill:#ffd54f,stroke:#ff6f00,stroke-width:4px,color:#000
    classDef optional fill:#fff,stroke:#999,stroke-width:2px,color:#333
    
    class A detail
    class B required
    class C,B1,C1 optional
```

---

## 11. Full CohortExpression Composition

**How it all fits together:** This diagram shows the complete assembly of a cohort definition. The flow is:

1. **ConceptSets** define what clinical concepts to search for
2. **PrimaryCriteria** identifies initial index events
3. **AdditionalCriteria** (CriteriaGroup) applies post-index event logic
4. **InclusionRules** add cross-cutting constraints (all must be satisfied)
5. **EndStrategy** specifies when patients exit
6. **CollapseSettings** determine how events are combined

The resulting cohort is:
- Reproducible (entire definition is portable JSON)
- Queryable against any OMOP CDM database
- Versioned and auditable

```mermaid
graph TD
    A["CohortExpression"]
    B["ConceptSet 0..N"]
    C["PrimaryCriteria"]
    D["CriteriaGroup<br/>AdditionalCriteria"]
    E["InclusionRule 0..N"]
    F["EndStrategy"]
    G["CollapseSettings"]
    H["ResultLimit"]
    
    C1["CriteriaType 1..N"]
    C2["ObservationFilter"]
    C3["ResultLimit"]
    
    D1["CorelatedCriteria 0..N"]
    D2["CriteriaGroup 0..N<br/>RECURSIVE"]
    D3["DemographicCriteria 0..N"]
    
    E1["name, description"]
    E2["expression CriteriaGroup"]
    
    F1["DateOffsetStrategy<br/>CustomEraStrategy"]
    
    G1["era_pad*"]
    G2["collapse_type"]
    
    A --> B
    A --> C
    A --> D
    A --> E
    A --> F
    A --> G
    A --> H
    C --> C1
    C --> C2
    C --> C3
    D --> D1
    D --> D2
    D --> D3
    E --> E1
    E --> E2
    F --> F1
    G --> G1
    G --> G2
    
    classDef root fill:#0277bd,stroke:#01579b,stroke-width:3px,color:#fff
    classDef container fill:#9c27b0,stroke:#6a1b9a,stroke-width:3px,color:#fff
    classDef detail fill:#2e7d32,stroke:#1b5e20,stroke-width:2px,color:#fff
    classDef required fill:#ffd54f,stroke:#ff6f00,stroke-width:4px,color:#000
    classDef optional fill:#fff,stroke:#999,stroke-width:2px,color:#333
    
    class A root
    class B,C,D,E,F,G,H container
    class C1,C2,C3,D1,D2,D3,E1,E2,F1 detail
    class G1 required
    class G2 optional
```

---

## Diagram Legend

See [How to Read These Diagrams](#how-to-read-these-diagrams) at the top of this document for the full color and field-marker legend.

---

## Module Organization

```
circe/cohortdefinition/
├── core.py                    # CirceBaseModel, Period, DateRange, NumericRange, etc.
├── cohort.py                  # CohortExpression (main model)
├── criteria.py                # All Criteria classes (16 types + supporting)
├── interfaces.py              # Abstract SQL generation interfaces
├── builders/                  # SQL generation implementations
├── sqlglot_builders/          # SQLGlot-based builders
└── printfriendly/             # Markdown/text output formatters

circe/vocabulary/
├── concept.py                 # Concept, ConceptSet, ConceptSetExpression
└── predicate_*.py             # Predicate handling
```

---

## Key Design Patterns

### 1. **Discriminated Union**
Criteria types are serialized with class names as discriminators:
```json
{
  "ConditionOccurrence": { "conceptIds": [...], "dateAdjustment": {...} },
  "DrugExposure": { "conceptIds": [...], "daysSupply": {...} }
}
```

### 2. **PascalCase Serialization**
All model fields use PascalCase in JSON output via `alias_generator` and field aliases.

### 3. **Recursive Structure**
`CriteriaGroup` can contain other `CriteriaGroup`s, enabling arbitrarily complex nested boolean logic.

### 4. **Polymorphic Strategy Pattern**
`EndStrategy` base class with multiple concrete strategies for cohort exit logic.

### 5. **Visitor Pattern**
Criteria classes implement `accept(dispatcher)` for SQL generation.

---

## Required vs Optional Fields

**Required fields** (marked with `*` in diagrams):
- `CohortExpression.concept_sets`
- `PrimaryCriteria.criteria_list`
- `ConceptSet.id`, `name`, `expression`
- `ConceptExpressionItem.concept`, `isExcluded`, `includeDescendants`, `includeMapped`
- `Concept.conceptId`
- `Occurrence.type`, `count`
- `CollapseSettings.era_pad`

All other fields are optional and serialize only when non-null.

---

## Example Cohort Definitions

### Simple Example: Type 2 Diabetes Patients

A straightforward cohort: patients with a diagnosis of Type 2 Diabetes within a 1-year observation period, with 365-day follow-up.

```yaml
title: Type 2 Diabetes Patients
cdmVersionRange:
  op: "gte"
  value: "5.3"

conceptSets:
  - id: 1
    name: "Type 2 Diabetes Diagnosis"
    expression:
      items:
        - concept:
            conceptId: 44809317
            conceptName: "Type 2 diabetes mellitus"
            domainId: "Condition"
          isExcluded: false
          includeDescendants: true
          includeMapped: false

primaryCriteria:
  criteriaList:
    - ConditionOccurrence:
        conceptSetId: 1
  observationWindow:
    priorDays: 0
    postDays: 0
  primaryLimit:
    type: "First"
    value: 1

additionalCriteria:
  criteriaList: []
  groups: []
  demographicCriteriaList: []
  type: "AND"

inclusionRules: []

censoring_criteria:
  criteriaList: []
  groups: []
  demographicCriteriaList: []
  type: "AND"

endStrategy:
  DateOffsetStrategy:
    daysOffset: 365

collapseSettings:
  collapsType: "ERA"
  eraPad: 0

expressionLimit:
  type: "All"
```

---

### Complex Example: Type 2 Diabetes with Hypertension, on Metformin

A multi-criteria cohort:
- **Entry**: Type 2 Diabetes diagnosis
- **Post-index inclusion**: Must have hypertension diagnosis within 90 days AND be on Metformin within 180 days
- **Demographics**: Restrict to adults (age 18+)
- **Exit**: 2 years after diagnosis
- **Collapsing**: Era-based with 30-day gap tolerance

```yaml
title: Type 2 Diabetes with Hypertension on Metformin
cdmVersionRange:
  op: "gte"
  value: "5.3"

conceptSets:
  - id: 1
    name: "Type 2 Diabetes Diagnosis"
    expression:
      items:
        - concept:
            conceptId: 44809317
            conceptName: "Type 2 diabetes mellitus"
          isExcluded: false
          includeDescendants: true
          includeMapped: false

  - id: 2
    name: "Hypertension Diagnosis"
    expression:
      items:
        - concept:
            conceptId: 316866
            conceptName: "Hypertension"
          isExcluded: false
          includeDescendants: true
          includeMapped: false

  - id: 3
    name: "Metformin Drug"
    expression:
      items:
        - concept:
            conceptId: 1551860
            conceptName: "Metformin"
          isExcluded: false
          includeDescendants: true
          includeMapped: true

primaryCriteria:
  criteriaList:
    - ConditionOccurrence:
        conceptSetId: 1
  observationWindow:
    priorDays: 365
    postDays: 0
  primaryLimit:
    type: "First"
    value: 1

additionalCriteria:
  criteriaList:
    # Metformin must start within 180 days after index
    - criteria:
        DrugExposure:
          conceptSetId: 3
      startWindow:
        start:
          coeff: 1
          days: 0
        end:
          coeff: 1
          days: 180
        useEventEnd: false
        useIndexEnd: false
      endWindow:
        start:
          coeff: 1
          days: 0
        end:
          coeff: 1
          days: 180
        useEventEnd: false
        useIndexEnd: false
      occurrence:
        type: 2  # AT_LEAST
        count: 1
        isDistinct: false

  groups:
    # Hypertension must occur within 90 days AND is a group with demographic filter
    - criteriaList:
        - criteria:
            ConditionOccurrence:
              conceptSetId: 2
          startWindow:
            start:
              coeff: 1
              days: 0
            end:
              coeff: 1
              days: 90
            useEventEnd: false
            useIndexEnd: false
          endWindow:
            start:
              coeff: 1
              days: 0
            end:
              coeff: 1
              days: 90
            useEventEnd: false
            useIndexEnd: false
          occurrence:
            type: 2  # AT_LEAST
            count: 1
            isDistinct: false

      groups: []
      demographicCriteriaList:
        - age:
            op: ">="
            value: 18
      type: "AND"
      count: 1

  demographicCriteriaList:
    - age:
        op: ">="
        value: 18
  type: "AND"

inclusionRules:
  - name: "No prior diabetes medication"
    description: "Exclude patients on any diabetes drug in the year before index"
    expression:
      criteriaList:
        - criteria:
            DrugExposure:
              conceptSetId: 3
          startWindow:
            start:
              coeff: 1
              days: -365
            end:
              coeff: 1
              days: 0
            useEventEnd: false
            useIndexEnd: false
          endWindow:
            start:
              coeff: 1
              days: -365
            end:
              coeff: 1
              days: 0
            useEventEnd: false
            useIndexEnd: false
          occurrence:
            type: 0  # EXACTLY
            count: 0
            isDistinct: false
      groups: []
      demographicCriteriaList: []
      type: "AND"

censoring_criteria:
  criteriaList: []
  groups: []
  demographicCriteriaList: []
  type: "AND"

endStrategy:
  DateOffsetStrategy:
    daysOffset: 730  # 2 years

collapseSettings:
  collapseType: "ERA"
  eraPad: 30

expressionLimit:
  type: "All"

qualifiedLimit:
  type: "All"
```

These examples demonstrate:
- **Simple**: Minimal required fields for a basic single-criterion cohort
- **Complex**: 
  - Multiple concept sets
  - Nested criteria groups with demographic filters
  - Time windows (30/90/180/365 days post-index)
  - Occurrence constraints (AT_LEAST, EXACTLY)
  - Inclusion rules (exclusion by absence of drug)
  - Era collapsing with gap tolerance
  - 2-year follow-up period

