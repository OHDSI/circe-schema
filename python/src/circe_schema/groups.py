from typing import TYPE_CHECKING, Any, List, Optional

from pydantic import AliasChoices, BaseModel, ConfigDict, Field, field_validator

from .base import CirceBaseModel
from .core import (
    ConceptSetSelection,
    DateRange,
    NumericRange,
    Window,
)
from .vocabulary import Concept

if TYPE_CHECKING:
    from .criteria import (
        ConditionEra,
        ConditionOccurrence,
        Death,
        DeviceExposure,
        DoseEra,
        DrugEra,
        DrugExposure,
        LocationRegion,
        Measurement,
        Observation,
        ObservationPeriod,
        PayerPlanPeriod,
        ProcedureOccurrence,
        Specimen,
        VisitDetail,
        VisitOccurrence,
    )


class Occurrence(CirceBaseModel):
    """Occurrence count parameters for correlated criteria.

    Defines how many times a criteria must occur (EXACTLY n, AT_MOST n, or AT_LEAST n).
    """
    type: int = Field(
        validation_alias=AliasChoices("Type", "type"),
        serialization_alias="Type",
    )
    count: int = Field(
        validation_alias=AliasChoices("Count", "count"),
        serialization_alias="Count",
    )
    is_distinct: bool = Field(
        default=False,
        validation_alias=AliasChoices("IsDistinct", "isDistinct"),
        serialization_alias="IsDistinct",
    )
    count_column: Optional[str] = Field(
        default=None,
        validation_alias=AliasChoices("CountColumn", "countColumn"),
        serialization_alias="CountColumn",
    )


class WindowedCriteria(CirceBaseModel):
    """A criteria with time windows defining when it must occur relative to an index date."""
    criteria: "CriteriaType" = Field(
        validation_alias=AliasChoices("Criteria", "criteria"),
        serialization_alias="Criteria",
    )
    start_window: Optional[Window] = Field(
        default=None,
        validation_alias=AliasChoices("StartWindow", "startWindow"),
        serialization_alias="StartWindow",
    )
    end_window: Optional[Window] = Field(
        default=None,
        validation_alias=AliasChoices("EndWindow", "endWindow"),
        serialization_alias="EndWindow",
    )
    restrict_visit: bool = Field(
        default=False,
        validation_alias=AliasChoices("RestrictVisit", "restrictVisit"),
        serialization_alias="RestrictVisit",
    )
    ignore_observation_period: bool = Field(
        default=False,
        validation_alias=AliasChoices("IgnoreObservationPeriod", "ignoreObservationPeriod"),
        serialization_alias="IgnoreObservationPeriod",
    )


class CorelatedCriteria(WindowedCriteria):
    """A criteria with occurrence parameters and time windows.

    Associates a domain criteria with start/end windows and occurrence constraints.
    NOTE: The name 'Corelated' is deliberately misspelled to match the Java implementation.
    """
    occurrence: Optional[Occurrence] = Field(
        default=None,
        validation_alias=AliasChoices("Occurrence", "occurrence"),
        serialization_alias="Occurrence",
    )


class DemographicCriteria(CirceBaseModel):
    """Demographic constraints for a criteria group.

    Filters based on patient demographics like age, gender, race, and ethnicity.
    """
    age: Optional[NumericRange] = Field(
        default=None,
        validation_alias=AliasChoices("Age", "age"),
        serialization_alias="Age",
    )
    gender: Optional[List[Concept]] = Field(
        default=None,
        validation_alias=AliasChoices("Gender", "gender"),
        serialization_alias="Gender",
    )
    gender_cs: Optional[ConceptSetSelection] = Field(
        default=None,
        validation_alias=AliasChoices("GenderCS", "genderCS"),
        serialization_alias="GenderCS",
    )
    race: Optional[List[Concept]] = Field(
        default=None,
        validation_alias=AliasChoices("Race", "race"),
        serialization_alias="Race",
    )
    race_cs: Optional[ConceptSetSelection] = Field(
        default=None,
        validation_alias=AliasChoices("RaceCS", "raceCS"),
        serialization_alias="RaceCS",
    )
    ethnicity: Optional[List[Concept]] = Field(
        default=None,
        validation_alias=AliasChoices("Ethnicity", "ethnicity"),
        serialization_alias="Ethnicity",
    )
    ethnicity_cs: Optional[ConceptSetSelection] = Field(
        default=None,
        validation_alias=AliasChoices("EthnicityCS", "ethnicityCS"),
        serialization_alias="EthnicityCS",
    )
    occurrence_start_date: Optional[DateRange] = Field(
        default=None,
        validation_alias=AliasChoices("OccurrenceStartDate", "occurrenceStartDate"),
        serialization_alias="OccurrenceStartDate",
    )
    occurrence_end_date: Optional[DateRange] = Field(
        default=None,
        validation_alias=AliasChoices("OccurrenceEndDate", "occurrenceEndDate"),
        serialization_alias="OccurrenceEndDate",
    )


class CriteriaGroup(BaseModel):
    """A group of criteria combined with logical operators.

    Supports nested groups, demographic criteria, and occurrence counts.
    """
    criteria_list: List["CorelatedCriteria"] = Field(
        default_factory=list,
        validation_alias=AliasChoices("CriteriaList", "criteriaList"),
        serialization_alias="CriteriaList",
    )
    count: Optional[int] = Field(
        default=None,
        validation_alias=AliasChoices("Count", "count"),
        serialization_alias="Count",
    )
    groups: List["CriteriaGroup"] = Field(
        default_factory=list,
        validation_alias=AliasChoices("Groups", "groups"),
        serialization_alias="Groups",
    )
    demographic_criteria_list: List[DemographicCriteria] = Field(
        default_factory=list,
        validation_alias=AliasChoices("DemographicCriteriaList", "demographicCriteriaList"),
        serialization_alias="DemographicCriteriaList",
    )
    type: Optional[str] = Field(
        default=None,
        validation_alias=AliasChoices("Type", "type"),
        serialization_alias="Type",
    )

    model_config = ConfigDict(populate_by_name=True)

    def is_empty(self) -> bool:
        """Check if this group has no criteria, subgroups, or demographics."""
        return not (
            (self.criteria_list and len(self.criteria_list) > 0)
            or (self.groups and len(self.groups) > 0)
            or (self.demographic_criteria_list and len(self.demographic_criteria_list) > 0)
        )


class InclusionRule(CirceBaseModel):
    """An inclusion rule determines which qualifying events become cohort records.

    All rules must be satisfied (AND logic).
    """
    expression: Optional["CriteriaGroup"] = Field(
        default=None,
        validation_alias=AliasChoices("Expression", "expression"),
        serialization_alias="expression",
    )
    description: Optional[str] = Field(
        default=None,
        validation_alias=AliasChoices("Description", "description"),
        serialization_alias="description",
    )
    name: Optional[str] = Field(
        default=None,
        validation_alias=AliasChoices("Name", "name"),
        serialization_alias="name",
    )


CriteriaType = Any
