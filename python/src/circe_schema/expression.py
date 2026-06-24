from typing import TYPE_CHECKING, Any, List, Optional, Union

from pydantic import AliasChoices, ConfigDict, Field, field_validator, model_validator

from .base import CirceBaseModel
from .core import (
    CollapseSettings,
    ObservationFilter,
    Period,
    ResultLimit,
)
from .end_strategy import CustomEraStrategy, DateOffsetStrategy, EndStrategy
from .groups import CriteriaGroup, CriteriaType, InclusionRule

if TYPE_CHECKING:
    from .vocabulary import ConceptSet
else:
    try:
        from .vocabulary import ConceptSet
    except ImportError:
        ConceptSet = Any


class PrimaryCriteria(CirceBaseModel):
    criteria_list: List[CriteriaType] = Field(
        default_factory=list,
        validation_alias=AliasChoices("CriteriaList", "criteriaList"),
        serialization_alias="CriteriaList",
    )
    observation_window: Optional[ObservationFilter] = Field(
        default=None,
        validation_alias=AliasChoices("ObservationWindow", "observationWindow"),
        serialization_alias="ObservationWindow",
    )
    primary_limit: Optional[ResultLimit] = Field(
        default=None,
        validation_alias=AliasChoices("PrimaryLimit", "primaryLimit"),
        serialization_alias="PrimaryLimit",
    )


class CohortExpression(CirceBaseModel):
    title: Optional[str] = Field(
        default=None,
        validation_alias=AliasChoices("Title", "title"),
        serialization_alias="Title",
    )
    primary_criteria: Optional[PrimaryCriteria] = Field(
        default=None,
        validation_alias=AliasChoices("PrimaryCriteria", "primaryCriteria"),
        serialization_alias="PrimaryCriteria",
    )
    additional_criteria: Optional[CriteriaGroup] = Field(
        default=None,
        validation_alias=AliasChoices("AdditionalCriteria", "additionalCriteria"),
        serialization_alias="AdditionalCriteria",
    )
    concept_sets: List[ConceptSet] = Field(
        default_factory=list,
        validation_alias=AliasChoices("ConceptSets", "conceptSets"),
        serialization_alias="ConceptSets",
    )
    qualified_limit: Optional[ResultLimit] = Field(
        default=None,
        validation_alias=AliasChoices("QualifiedLimit", "qualifiedLimit"),
        serialization_alias="QualifiedLimit",
    )
    expression_limit: Optional[ResultLimit] = Field(
        default=None,
        validation_alias=AliasChoices("ExpressionLimit", "expressionLimit"),
        serialization_alias="ExpressionLimit",
    )
    inclusion_rules: List[InclusionRule] = Field(
        default_factory=list,
        validation_alias=AliasChoices("InclusionRules", "inclusionRules"),
        serialization_alias="InclusionRules",
    )
    end_strategy: Optional[Union[EndStrategy, DateOffsetStrategy, CustomEraStrategy]] = Field(
        default=None,
        validation_alias=AliasChoices("EndStrategy", "endStrategy"),
        serialization_alias="EndStrategy",
    )
    censoring_criteria: List[CriteriaType] = Field(
        default_factory=list,
        validation_alias=AliasChoices("CensoringCriteria", "censoring_criteria", "censoringCriteria"),
        serialization_alias="CensoringCriteria",
    )
    collapse_settings: Optional[CollapseSettings] = Field(
        default=None,
        validation_alias=AliasChoices("CollapseSettings", "collapseSettings"),
        serialization_alias="CollapseSettings",
    )
    censor_window: Optional[Period] = Field(
        default=None,
        validation_alias=AliasChoices("CensorWindow", "censorWindow"),
        serialization_alias="CensorWindow",
    )
    cdm_version_range: Optional[str] = Field(
        default=None,
        alias="cdmVersionRange",
    )

    model_config = ConfigDict(populate_by_name=True)

    @field_validator("inclusion_rules", mode="before")
    @classmethod
    def allow_none_inclusion_rules(cls, v: Any) -> Any:
        return [] if v is None else v

    @field_validator("concept_sets", mode="before")
    @classmethod
    def allow_none_concept_sets(cls, v: Any) -> Any:
        return [] if v is None else v

    @field_validator("censoring_criteria", mode="before")
    @classmethod
    def allow_none_censoring_criteria(cls, v: Any) -> Any:
        return [] if v is None else v

    @field_validator("end_strategy", mode="before")
    @classmethod
    def deserialize_end_strategy(cls, v: Any) -> Any:
        if not v or not isinstance(v, dict):
            return v
        if "DateOffset" in v:
            return DateOffsetStrategy.model_validate(v["DateOffset"], strict=False)
        if "CustomEra" in v:
            return CustomEraStrategy.model_validate(v["CustomEra"], strict=False)
        return EndStrategy.model_validate(v, strict=False)

    @field_validator("censoring_criteria", mode="before")
    @classmethod
    def deserialize_censoring_criteria(cls, v: Any) -> Any:
        if v is None:
            return []
        if not v or not isinstance(v, list):
            return v

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

        criteria_class_map = {
            "ConditionOccurrence": ConditionOccurrence,
            "DrugExposure": DrugExposure,
            "ProcedureOccurrence": ProcedureOccurrence,
            "VisitOccurrence": VisitOccurrence,
            "Observation": Observation,
            "Measurement": Measurement,
            "DeviceExposure": DeviceExposure,
            "Specimen": Specimen,
            "Death": Death,
            "VisitDetail": VisitDetail,
            "ObservationPeriod": ObservationPeriod,
            "PayerPlanPeriod": PayerPlanPeriod,
            "LocationRegion": LocationRegion,
            "ConditionEra": ConditionEra,
            "DrugEra": DrugEra,
            "DoseEra": DoseEra,
        }

        deserialized = []
        for item in v:
            if not isinstance(item, dict):
                deserialized.append(item)
                continue

            criteria_type = None
            criteria_data = None
            for key in item.keys():
                if key in criteria_class_map:
                    criteria_type = key
                    criteria_data = item[key]
                    break

            if criteria_type and criteria_data is not None:
                data_copy = dict(criteria_data) if criteria_data else {}
                if "First" not in data_copy and "first" not in data_copy:
                    data_copy["First"] = False
                if criteria_type == "Measurement" and "MeasurementTypeExclude" not in data_copy and "measurementTypeExclude" not in data_copy:
                    data_copy["MeasurementTypeExclude"] = False
                if criteria_type == "Observation" and "ObservationTypeExclude" not in data_copy and "observationTypeExclude" not in data_copy:
                    data_copy["ObservationTypeExclude"] = False
                if criteria_type == "ConditionOccurrence" and "ConditionTypeExclude" not in data_copy and "conditionTypeExclude" not in data_copy:
                    data_copy["ConditionTypeExclude"] = False
                criteria_obj = criteria_class_map[criteria_type].model_validate(data_copy, strict=False)
                deserialized.append(criteria_obj)
            else:
                deserialized.append(item)

        return deserialized

    @field_validator("primary_criteria", mode="before")
    @classmethod
    def deserialize_primary_criteria(cls, v: Any) -> Any:
        if not v or not isinstance(v, dict):
            return v
        result = dict(v)
        if "CriteriaList" in result:
            criteria_list = result.get("CriteriaList", [])
            if criteria_list and isinstance(criteria_list, list):
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
                criteria_class_map = {
                    "ConditionOccurrence": ConditionOccurrence,
                    "DrugExposure": DrugExposure,
                    "ProcedureOccurrence": ProcedureOccurrence,
                    "VisitOccurrence": VisitOccurrence,
                    "Observation": Observation,
                    "Measurement": Measurement,
                    "DeviceExposure": DeviceExposure,
                    "Specimen": Specimen,
                    "Death": Death,
                    "VisitDetail": VisitDetail,
                    "ObservationPeriod": ObservationPeriod,
                    "PayerPlanPeriod": PayerPlanPeriod,
                    "LocationRegion": LocationRegion,
                    "ConditionEra": ConditionEra,
                    "DrugEra": DrugEra,
                    "DoseEra": DoseEra,
                }
                deserialized = []
                for item in criteria_list:
                    if not isinstance(item, dict):
                        deserialized.append(item)
                        continue
                    criteria_type = None
                    criteria_data = None
                    for key in item.keys():
                        if key in criteria_class_map:
                            criteria_type = key
                            criteria_data = item[key]
                            break
                    if criteria_type and criteria_data is not None:
                        data_copy = dict(criteria_data) if criteria_data else {}
                        if criteria_type == "Measurement" and "MeasurementTypeExclude" not in data_copy and "measurementTypeExclude" not in data_copy:
                            data_copy["MeasurementTypeExclude"] = False
                        if criteria_type == "ConditionOccurrence" and "ConditionTypeExclude" not in data_copy and "conditionTypeExclude" not in data_copy:
                            data_copy["ConditionTypeExclude"] = False
                        criteria_obj = criteria_class_map[criteria_type].model_validate(data_copy, strict=False)
                        deserialized.append(criteria_obj)
                    else:
                        deserialized.append(item)
                result["CriteriaList"] = deserialized
        return result

    @field_validator("additional_criteria", mode="before")
    @classmethod
    def deserialize_additional_criteria(cls, v: Any) -> Any:
        if not v or not isinstance(v, dict):
            return v
        return cls._deserialize_criteria_group(dict(v))

    @classmethod
    def _deserialize_criteria_group(cls, data: dict) -> Any:
        result = dict(data)
        if "CriteriaList" in result and result["CriteriaList"]:
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
            criteria_class_map = {
                "ConditionOccurrence": ConditionOccurrence,
                "DrugExposure": DrugExposure,
                "ProcedureOccurrence": ProcedureOccurrence,
                "VisitOccurrence": VisitOccurrence,
                "Observation": Observation,
                "Measurement": Measurement,
                "DeviceExposure": DeviceExposure,
                "Specimen": Specimen,
                "Death": Death,
                "VisitDetail": VisitDetail,
                "ObservationPeriod": ObservationPeriod,
                "PayerPlanPeriod": PayerPlanPeriod,
                "LocationRegion": LocationRegion,
                "ConditionEra": ConditionEra,
                "DrugEra": DrugEra,
                "DoseEra": DoseEra,
            }
            criteria_list = list(result["CriteriaList"])
            for i, item in enumerate(criteria_list):
                if isinstance(item, dict):
                    criteria_type = None
                    criteria_data = None
                    for key in item.keys():
                        if key in criteria_class_map:
                            criteria_type = key
                            criteria_data = item[key]
                            break
                    if criteria_type and criteria_data is not None:
                        data_copy = dict(criteria_data) if criteria_data else {}
                        if criteria_type not in ("Death",):
                            if "First" not in data_copy and "first" not in data_copy:
                                data_copy["First"] = False
                        if criteria_type == "Measurement" and "MeasurementTypeExclude" not in data_copy and "measurementTypeExclude" not in data_copy:
                            data_copy["MeasurementTypeExclude"] = False
                        if criteria_type == "Observation" and "ObservationTypeExclude" not in data_copy and "observationTypeExclude" not in data_copy:
                            data_copy["ObservationTypeExclude"] = False
                        if criteria_type == "ConditionOccurrence" and "ConditionTypeExclude" not in data_copy and "conditionTypeExclude" not in data_copy:
                            data_copy["ConditionTypeExclude"] = False
                        criteria_obj = criteria_class_map[criteria_type].model_validate(data_copy, strict=False)
                        criteria_list[i] = {"Criteria": criteria_obj}
            result["CriteriaList"] = criteria_list
        if "Groups" in result and result["Groups"]:
            groups = list(result["Groups"])
            for i, group in enumerate(groups):
                if isinstance(group, dict):
                    groups[i] = cls._deserialize_criteria_group(group)
            result["Groups"] = groups
        return result

    def validate_expression(self) -> bool:
        if not self.primary_criteria:
            return False
        if self.concept_sets:
            for cs in self.concept_sets:
                if cs.id is None:
                    return False
        return True
