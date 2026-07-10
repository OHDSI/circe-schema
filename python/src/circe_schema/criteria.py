from typing import TYPE_CHECKING, Any, List, Optional

from pydantic import AliasChoices, Field, model_serializer

from .base import CirceBaseModel
from .core import (
    ConceptSetSelection,
    DateAdjustment,
    DateRange,
    NumericRange,
    Period,
    TextFilter,
)
from .vocabulary import Concept

if TYPE_CHECKING:
    from .groups import CriteriaGroup


class Criteria(CirceBaseModel):
    """Base criteria type for all domain-specific cohort entry criteria.

    All domain-specific criteria inherit from this. In JSON, each criteria is serialized
    with a polymorphic wrapper: ``{"ConditionOccurrence": {...}}``. The discriminator
    is the single key in the object.
    """
    date_adjustment: Optional[DateAdjustment] = Field(
        default=None,
        validation_alias=AliasChoices("DateAdjustment", "dateAdjustment"),
        serialization_alias="DateAdjustment",
    )
    correlated_criteria: Optional["CriteriaGroup"] = Field(
        default=None,
        validation_alias=AliasChoices("CorrelatedCriteria", "correlatedCriteria"),
        serialization_alias="CorrelatedCriteria",
    )

    @model_serializer(mode="wrap")
    def _serialize_polymorphic(self, serializer, info):
        data = serializer(self)
        if self.__class__.__name__ != "Criteria":
            return {self.__class__.__name__: data}
        return data


class ConditionOccurrence(Criteria):
    """Condition occurrence criteria. Filters on condition diagnosis records in the OMOP CDM."""
    codeset_id: Optional[int] = Field(default=None, validation_alias=AliasChoices("CodesetId", "codesetId"), serialization_alias="CodesetId")
    first: Optional[bool] = Field(default=None, validation_alias=AliasChoices("First", "first"), serialization_alias="First")
    occurrence_start_date: Optional[DateRange] = Field(default=None, validation_alias=AliasChoices("OccurrenceStartDate", "occurrenceStartDate"), serialization_alias="OccurrenceStartDate")
    occurrence_end_date: Optional[DateRange] = Field(default=None, validation_alias=AliasChoices("OccurrenceEndDate", "occurrenceEndDate"), serialization_alias="OccurrenceEndDate")
    condition_type: Optional[List[Concept]] = Field(default=None, validation_alias=AliasChoices("ConditionType", "conditionType"), serialization_alias="ConditionType")
    condition_type_cs: Optional[ConceptSetSelection] = Field(default=None, validation_alias=AliasChoices("ConditionTypeCS", "conditionTypeCS"), serialization_alias="ConditionTypeCS")
    condition_type_exclude: bool = Field(default=False, validation_alias=AliasChoices("ConditionTypeExclude", "conditionTypeExclude"), serialization_alias="ConditionTypeExclude")
    stop_reason: Optional[TextFilter] = Field(default=None, validation_alias=AliasChoices("StopReason", "stopReason"), serialization_alias="StopReason")
    condition_source_concept: Optional[int] = Field(default=None, validation_alias=AliasChoices("ConditionSourceConcept", "conditionSourceConcept"), serialization_alias="ConditionSourceConcept")
    age: Optional[NumericRange] = Field(default=None, validation_alias=AliasChoices("Age", "age"), serialization_alias="Age")
    gender: Optional[List[Concept]] = Field(default=None, validation_alias=AliasChoices("Gender", "gender"), serialization_alias="Gender")
    gender_cs: Optional[ConceptSetSelection] = Field(default=None, validation_alias=AliasChoices("GenderCS", "genderCS"), serialization_alias="GenderCS")
    provider_specialty: Optional[List[Concept]] = Field(default=None, validation_alias=AliasChoices("ProviderSpecialty", "providerSpecialty"), serialization_alias="ProviderSpecialty")
    provider_specialty_cs: Optional[ConceptSetSelection] = Field(default=None, validation_alias=AliasChoices("ProviderSpecialtyCS", "providerSpecialtyCS"), serialization_alias="ProviderSpecialtyCS")
    visit_type: Optional[List[Concept]] = Field(default=None, validation_alias=AliasChoices("VisitType", "visitType"), serialization_alias="VisitType")
    visit_type_cs: Optional[ConceptSetSelection] = Field(default=None, validation_alias=AliasChoices("VisitTypeCS", "visitTypeCS"), serialization_alias="VisitTypeCS")
    condition_status: Optional[List[Concept]] = Field(default=None, validation_alias=AliasChoices("ConditionStatus", "conditionStatus"), serialization_alias="ConditionStatus")
    condition_status_cs: Optional[ConceptSetSelection] = Field(default=None, validation_alias=AliasChoices("ConditionStatusCS", "conditionStatusCS"), serialization_alias="ConditionStatusCS")


class DrugExposure(Criteria):
    """Drug exposure criteria. Filters on drug administration records in the OMOP CDM."""
    codeset_id: Optional[int] = Field(default=None, validation_alias=AliasChoices("CodesetId", "codesetId"), serialization_alias="CodesetId")
    first: Optional[bool] = Field(default=None, validation_alias=AliasChoices("First", "first"), serialization_alias="First")
    occurrence_start_date: Optional[DateRange] = Field(default=None, validation_alias=AliasChoices("OccurrenceStartDate", "occurrenceStartDate"), serialization_alias="OccurrenceStartDate")
    occurrence_end_date: Optional[DateRange] = Field(default=None, validation_alias=AliasChoices("OccurrenceEndDate", "occurrenceEndDate"), serialization_alias="OccurrenceEndDate")
    drug_type: Optional[List[Concept]] = Field(default=None, validation_alias=AliasChoices("DrugType", "drugType"), serialization_alias="DrugType")
    drug_type_cs: Optional[ConceptSetSelection] = Field(default=None, validation_alias=AliasChoices("DrugTypeCS", "drugTypeCS"), serialization_alias="DrugTypeCS")
    drug_type_exclude: bool = Field(default=False, validation_alias=AliasChoices("DrugTypeExclude", "drugTypeExclude"), serialization_alias="DrugTypeExclude")
    drug_source_concept: Optional[int] = Field(default=None, validation_alias=AliasChoices("DrugSourceConcept", "drugSourceConcept"), serialization_alias="DrugSourceConcept")
    stop_reason: Optional[TextFilter] = Field(default=None, validation_alias=AliasChoices("StopReason", "stopReason"), serialization_alias="StopReason")
    age: Optional[NumericRange] = Field(default=None, validation_alias=AliasChoices("Age", "age"), serialization_alias="Age")
    gender: Optional[List[Concept]] = Field(default=None, validation_alias=AliasChoices("Gender", "gender"), serialization_alias="Gender")
    gender_cs: Optional[ConceptSetSelection] = Field(default=None, validation_alias=AliasChoices("GenderCS", "genderCS"), serialization_alias="GenderCS")
    provider_specialty: Optional[List[Concept]] = Field(default=None, validation_alias=AliasChoices("ProviderSpecialty", "providerSpecialty"), serialization_alias="ProviderSpecialty")
    provider_specialty_cs: Optional[ConceptSetSelection] = Field(default=None, validation_alias=AliasChoices("ProviderSpecialtyCS", "providerSpecialtyCS"), serialization_alias="ProviderSpecialtyCS")
    visit_type: Optional[List[Concept]] = Field(default=None, validation_alias=AliasChoices("VisitType", "visitType"), serialization_alias="VisitType")
    visit_type_cs: Optional[ConceptSetSelection] = Field(default=None, validation_alias=AliasChoices("VisitTypeCS", "visitTypeCS"), serialization_alias="VisitTypeCS")
    route_concept: Optional[List[Concept]] = Field(default=None, validation_alias=AliasChoices("RouteConcept", "routeConcept"), serialization_alias="RouteConcept")
    route_concept_cs: Optional[ConceptSetSelection] = Field(default=None, validation_alias=AliasChoices("RouteConceptCS", "routeConceptCS"), serialization_alias="RouteConceptCS")
    quantity: Optional[NumericRange] = Field(default=None, validation_alias=AliasChoices("Quantity", "quantity"), serialization_alias="Quantity")
    days_supply: Optional[NumericRange] = Field(default=None, validation_alias=AliasChoices("DaysSupply", "daysSupply"), serialization_alias="DaysSupply")
    refills: Optional[NumericRange] = Field(default=None, validation_alias=AliasChoices("Refills", "refills"), serialization_alias="Refills")
    effective_drug_dose: Optional[NumericRange] = Field(default=None, validation_alias=AliasChoices("EffectiveDrugDose", "effectiveDrugDose"), serialization_alias="EffectiveDrugDose")
    dose_unit: Optional[List[Concept]] = Field(default=None, validation_alias=AliasChoices("DoseUnit", "doseUnit"), serialization_alias="DoseUnit")
    dose_unit_cs: Optional[ConceptSetSelection] = Field(default=None, validation_alias=AliasChoices("DoseUnitCS", "doseUnitCS"), serialization_alias="DoseUnitCS")
    lot_number: Optional[TextFilter] = Field(default=None, validation_alias=AliasChoices("LotNumber", "lotNumber"), serialization_alias="LotNumber")


class ProcedureOccurrence(Criteria):
    """Procedure occurrence criteria. Filters on procedure records in the OMOP CDM."""
    codeset_id: Optional[int] = Field(default=None, validation_alias=AliasChoices("CodesetId", "codesetId"), serialization_alias="CodesetId")
    first: Optional[bool] = Field(default=None, validation_alias=AliasChoices("First", "first"), serialization_alias="First")
    occurrence_start_date: Optional[DateRange] = Field(default=None, validation_alias=AliasChoices("OccurrenceStartDate", "occurrenceStartDate"), serialization_alias="OccurrenceStartDate")
    occurrence_end_date: Optional[DateRange] = Field(default=None, validation_alias=AliasChoices("OccurrenceEndDate", "occurrenceEndDate"), serialization_alias="OccurrenceEndDate")
    procedure_type: Optional[List[Concept]] = Field(default=None, validation_alias=AliasChoices("ProcedureType", "procedureType"), serialization_alias="ProcedureType")
    procedure_type_cs: Optional[ConceptSetSelection] = Field(default=None, validation_alias=AliasChoices("ProcedureTypeCS", "procedureTypeCS"), serialization_alias="ProcedureTypeCS")
    procedure_type_exclude: bool = Field(default=False, validation_alias=AliasChoices("ProcedureTypeExclude", "procedureTypeExclude"), serialization_alias="ProcedureTypeExclude")
    procedure_source_concept: Optional[int] = Field(default=None, validation_alias=AliasChoices("ProcedureSourceConcept", "procedureSourceConcept"), serialization_alias="ProcedureSourceConcept")
    quantity: Optional[NumericRange] = Field(default=None, validation_alias=AliasChoices("Quantity", "quantity"), serialization_alias="Quantity")
    age: Optional[NumericRange] = Field(default=None, validation_alias=AliasChoices("Age", "age"), serialization_alias="Age")
    gender: Optional[List[Concept]] = Field(default=None, validation_alias=AliasChoices("Gender", "gender"), serialization_alias="Gender")
    gender_cs: Optional[ConceptSetSelection] = Field(default=None, validation_alias=AliasChoices("GenderCS", "genderCS"), serialization_alias="GenderCS")
    provider_specialty: Optional[List[Concept]] = Field(default=None, validation_alias=AliasChoices("ProviderSpecialty", "providerSpecialty"), serialization_alias="ProviderSpecialty")
    provider_specialty_cs: Optional[ConceptSetSelection] = Field(default=None, validation_alias=AliasChoices("ProviderSpecialtyCS", "providerSpecialtyCS"), serialization_alias="ProviderSpecialtyCS")
    visit_type: Optional[List[Concept]] = Field(default=None, validation_alias=AliasChoices("VisitType", "visitType"), serialization_alias="VisitType")
    visit_type_cs: Optional[ConceptSetSelection] = Field(default=None, validation_alias=AliasChoices("VisitTypeCS", "visitTypeCS"), serialization_alias="VisitTypeCS")
    modifier: Optional[List[Concept]] = Field(default=None, validation_alias=AliasChoices("Modifier", "modifier"), serialization_alias="Modifier")
    modifier_cs: Optional[ConceptSetSelection] = Field(default=None, validation_alias=AliasChoices("ModifierCS", "modifierCS"), serialization_alias="ModifierCS")


class VisitOccurrence(Criteria):
    """Visit occurrence criteria. Filters on visit records in the OMOP CDM."""
    codeset_id: Optional[int] = Field(default=None, validation_alias=AliasChoices("CodesetId", "codesetId"), serialization_alias="CodesetId")
    first: Optional[bool] = Field(default=None, validation_alias=AliasChoices("First", "first"), serialization_alias="First")
    occurrence_start_date: Optional[DateRange] = Field(default=None, validation_alias=AliasChoices("OccurrenceStartDate", "occurrenceStartDate"), serialization_alias="OccurrenceStartDate")
    occurrence_end_date: Optional[DateRange] = Field(default=None, validation_alias=AliasChoices("OccurrenceEndDate", "occurrenceEndDate"), serialization_alias="OccurrenceEndDate")
    visit_type: Optional[List[Concept]] = Field(default=None, validation_alias=AliasChoices("VisitType", "visitType"), serialization_alias="VisitType")
    visit_type_cs: Optional[ConceptSetSelection] = Field(default=None, validation_alias=AliasChoices("VisitTypeCS", "visitTypeCS"), serialization_alias="VisitTypeCS")
    visit_type_exclude: bool = Field(default=False, validation_alias=AliasChoices("VisitTypeExclude", "visitTypeExclude"), serialization_alias="VisitTypeExclude")
    visit_source_concept: Optional[int] = Field(default=None, validation_alias=AliasChoices("VisitSourceConcept", "visitSourceConcept"), serialization_alias="VisitSourceConcept")
    visit_length: Optional[NumericRange] = Field(default=None, validation_alias=AliasChoices("VisitLength", "visitLength"), serialization_alias="VisitLength")
    age: Optional[NumericRange] = Field(default=None, validation_alias=AliasChoices("Age", "age"), serialization_alias="Age")
    gender: Optional[List[Concept]] = Field(default=None, validation_alias=AliasChoices("Gender", "gender"), serialization_alias="Gender")
    gender_cs: Optional[ConceptSetSelection] = Field(default=None, validation_alias=AliasChoices("GenderCS", "genderCS"), serialization_alias="GenderCS")
    provider_specialty: Optional[List[Concept]] = Field(default=None, validation_alias=AliasChoices("ProviderSpecialty", "providerSpecialty"), serialization_alias="ProviderSpecialty")
    provider_specialty_cs: Optional[ConceptSetSelection] = Field(default=None, validation_alias=AliasChoices("ProviderSpecialtyCS", "providerSpecialtyCS"), serialization_alias="ProviderSpecialtyCS")
    place_of_service: Optional[List[Concept]] = Field(default=None, validation_alias=AliasChoices("PlaceOfService", "placeOfService"), serialization_alias="PlaceOfService")
    place_of_service_cs: Optional[ConceptSetSelection] = Field(default=None, validation_alias=AliasChoices("PlaceOfServiceCS", "placeOfServiceCS"), serialization_alias="PlaceOfServiceCS")
    place_of_service_location: Optional[int] = Field(default=None, validation_alias=AliasChoices("PlaceOfServiceLocation", "placeOfServiceLocation"), serialization_alias="PlaceOfServiceLocation")


class Observation(Criteria):
    """Observation criteria. Filters on observation records in the OMOP CDM."""
    codeset_id: Optional[int] = Field(default=None, validation_alias=AliasChoices("CodesetId", "codesetId"), serialization_alias="CodesetId")
    first: Optional[bool] = Field(default=None, validation_alias=AliasChoices("First", "first"), serialization_alias="First")
    occurrence_start_date: Optional[DateRange] = Field(default=None, validation_alias=AliasChoices("OccurrenceStartDate", "occurrenceStartDate"), serialization_alias="OccurrenceStartDate")
    occurrence_end_date: Optional[DateRange] = Field(default=None, validation_alias=AliasChoices("OccurrenceEndDate", "occurrenceEndDate"), serialization_alias="OccurrenceEndDate")
    observation_type: Optional[List[Concept]] = Field(default=None, validation_alias=AliasChoices("ObservationType", "observationType"), serialization_alias="ObservationType")
    observation_type_cs: Optional[ConceptSetSelection] = Field(default=None, validation_alias=AliasChoices("ObservationTypeCS", "observationTypeCS"), serialization_alias="ObservationTypeCS")
    observation_type_exclude: bool = Field(default=False, validation_alias=AliasChoices("ObservationTypeExclude", "observationTypeExclude"), serialization_alias="ObservationTypeExclude")
    observation_source_concept: Optional[int] = Field(default=None, validation_alias=AliasChoices("ObservationSourceConcept", "observationSourceConcept"), serialization_alias="ObservationSourceConcept")
    value_as_number: Optional[NumericRange] = Field(default=None, validation_alias=AliasChoices("ValueAsNumber", "valueAsNumber"), serialization_alias="ValueAsNumber")
    value_as_string: Optional[TextFilter] = Field(default=None, validation_alias=AliasChoices("ValueAsString", "valueAsString"), serialization_alias="ValueAsString")
    value_as_concept: Optional[List[Concept]] = Field(default=None, validation_alias=AliasChoices("ValueAsConcept", "valueAsConcept"), serialization_alias="ValueAsConcept")
    value_as_concept_cs: Optional[ConceptSetSelection] = Field(default=None, validation_alias=AliasChoices("ValueAsConceptCS", "valueAsConceptCS"), serialization_alias="ValueAsConceptCS")
    unit: Optional[List[Concept]] = Field(default=None, validation_alias=AliasChoices("Unit", "unit"), serialization_alias="Unit")
    unit_cs: Optional[ConceptSetSelection] = Field(default=None, validation_alias=AliasChoices("UnitCS", "unitCS"), serialization_alias="UnitCS")
    qualifier: Optional[List[Concept]] = Field(default=None, validation_alias=AliasChoices("Qualifier", "qualifier"), serialization_alias="Qualifier")
    qualifier_cs: Optional[ConceptSetSelection] = Field(default=None, validation_alias=AliasChoices("QualifierCS", "qualifierCS"), serialization_alias="QualifierCS")
    age: Optional[NumericRange] = Field(default=None, validation_alias=AliasChoices("Age", "age"), serialization_alias="Age")
    gender: Optional[List[Concept]] = Field(default=None, validation_alias=AliasChoices("Gender", "gender"), serialization_alias="Gender")
    gender_cs: Optional[ConceptSetSelection] = Field(default=None, validation_alias=AliasChoices("GenderCS", "genderCS"), serialization_alias="GenderCS")
    provider_specialty: Optional[List[Concept]] = Field(default=None, validation_alias=AliasChoices("ProviderSpecialty", "providerSpecialty"), serialization_alias="ProviderSpecialty")
    provider_specialty_cs: Optional[ConceptSetSelection] = Field(default=None, validation_alias=AliasChoices("ProviderSpecialtyCS", "providerSpecialtyCS"), serialization_alias="ProviderSpecialtyCS")
    visit_type: Optional[List[Concept]] = Field(default=None, validation_alias=AliasChoices("VisitType", "visitType"), serialization_alias="VisitType")
    visit_type_cs: Optional[ConceptSetSelection] = Field(default=None, validation_alias=AliasChoices("VisitTypeCS", "visitTypeCS"), serialization_alias="VisitTypeCS")


class Measurement(Criteria):
    """Measurement criteria. Filters on measurement records in the OMOP CDM."""
    codeset_id: Optional[int] = Field(default=None, validation_alias=AliasChoices("CodesetId", "codesetId"), serialization_alias="CodesetId")
    first: Optional[bool] = Field(default=None, validation_alias=AliasChoices("First", "first"), serialization_alias="First")
    occurrence_start_date: Optional[DateRange] = Field(default=None, validation_alias=AliasChoices("OccurrenceStartDate", "occurrenceStartDate"), serialization_alias="OccurrenceStartDate")
    occurrence_end_date: Optional[DateRange] = Field(default=None, validation_alias=AliasChoices("OccurrenceEndDate", "occurrenceEndDate"), serialization_alias="OccurrenceEndDate")
    measurement_type: Optional[List[Concept]] = Field(default=None, validation_alias=AliasChoices("MeasurementType", "measurementType"), serialization_alias="MeasurementType")
    measurement_type_cs: Optional[ConceptSetSelection] = Field(default=None, validation_alias=AliasChoices("MeasurementTypeCS", "measurementTypeCS"), serialization_alias="MeasurementTypeCS")
    measurement_type_exclude: bool = Field(default=False, validation_alias=AliasChoices("MeasurementTypeExclude", "measurementTypeExclude"), serialization_alias="MeasurementTypeExclude")
    measurement_source_concept: Optional[int] = Field(default=None, validation_alias=AliasChoices("MeasurementSourceConcept", "measurementSourceConcept"), serialization_alias="MeasurementSourceConcept")
    value_as_number: Optional[NumericRange] = Field(default=None, validation_alias=AliasChoices("ValueAsNumber", "valueAsNumber"), serialization_alias="ValueAsNumber")
    value_as_string: Optional[TextFilter] = Field(default=None, validation_alias=AliasChoices("ValueAsString", "valueAsString"), serialization_alias="ValueAsString")
    value_as_concept: Optional[List[Concept]] = Field(default=None, validation_alias=AliasChoices("ValueAsConcept", "valueAsConcept"), serialization_alias="ValueAsConcept")
    value_as_concept_cs: Optional[ConceptSetSelection] = Field(default=None, validation_alias=AliasChoices("ValueAsConceptCS", "valueAsConceptCS"), serialization_alias="ValueAsConceptCS")
    operator: Optional[List[Concept]] = Field(default=None, validation_alias=AliasChoices("Operator", "operator"), serialization_alias="Operator")
    operator_cs: Optional[ConceptSetSelection] = Field(default=None, validation_alias=AliasChoices("OperatorCS", "operatorCS"), serialization_alias="OperatorCS")
    unit: Optional[List[Concept]] = Field(default=None, validation_alias=AliasChoices("Unit", "unit"), serialization_alias="Unit")
    unit_cs: Optional[ConceptSetSelection] = Field(default=None, validation_alias=AliasChoices("UnitCS", "unitCS"), serialization_alias="UnitCS")
    range_low: Optional[NumericRange] = Field(default=None, validation_alias=AliasChoices("RangeLow", "rangeLow"), serialization_alias="RangeLow")
    range_high: Optional[NumericRange] = Field(default=None, validation_alias=AliasChoices("RangeHigh", "rangeHigh"), serialization_alias="RangeHigh")
    range_low_ratio: Optional[NumericRange] = Field(default=None, validation_alias=AliasChoices("RangeLowRatio", "rangeLowRatio"), serialization_alias="RangeLowRatio")
    range_high_ratio: Optional[NumericRange] = Field(default=None, validation_alias=AliasChoices("RangeHighRatio", "rangeHighRatio"), serialization_alias="RangeHighRatio")
    abnormal: Optional[bool] = Field(default=None, validation_alias=AliasChoices("Abnormal", "abnormal"), serialization_alias="Abnormal")
    age: Optional[NumericRange] = Field(default=None, validation_alias=AliasChoices("Age", "age"), serialization_alias="Age")
    gender: Optional[List[Concept]] = Field(default=None, validation_alias=AliasChoices("Gender", "gender"), serialization_alias="Gender")
    gender_cs: Optional[ConceptSetSelection] = Field(default=None, validation_alias=AliasChoices("GenderCS", "genderCS"), serialization_alias="GenderCS")
    provider_specialty: Optional[List[Concept]] = Field(default=None, validation_alias=AliasChoices("ProviderSpecialty", "providerSpecialty"), serialization_alias="ProviderSpecialty")
    provider_specialty_cs: Optional[ConceptSetSelection] = Field(default=None, validation_alias=AliasChoices("ProviderSpecialtyCS", "providerSpecialtyCS"), serialization_alias="ProviderSpecialtyCS")
    visit_type: Optional[List[Concept]] = Field(default=None, validation_alias=AliasChoices("VisitType", "visitType"), serialization_alias="VisitType")
    visit_type_cs: Optional[ConceptSetSelection] = Field(default=None, validation_alias=AliasChoices("VisitTypeCS", "visitTypeCS"), serialization_alias="VisitTypeCS")


class DeviceExposure(Criteria):
    """Device exposure criteria. Filters on device exposure records in the OMOP CDM."""
    codeset_id: Optional[int] = Field(default=None, validation_alias=AliasChoices("CodesetId", "codesetId"), serialization_alias="CodesetId")
    first: Optional[bool] = Field(default=None, validation_alias=AliasChoices("First", "first"), serialization_alias="First")
    occurrence_start_date: Optional[DateRange] = Field(default=None, validation_alias=AliasChoices("OccurrenceStartDate", "occurrenceStartDate"), serialization_alias="OccurrenceStartDate")
    occurrence_end_date: Optional[DateRange] = Field(default=None, validation_alias=AliasChoices("OccurrenceEndDate", "occurrenceEndDate"), serialization_alias="OccurrenceEndDate")
    device_type: Optional[List[Concept]] = Field(default=None, validation_alias=AliasChoices("DeviceType", "deviceType"), serialization_alias="DeviceType")
    device_type_cs: Optional[ConceptSetSelection] = Field(default=None, validation_alias=AliasChoices("DeviceTypeCS", "deviceTypeCS"), serialization_alias="DeviceTypeCS")
    device_type_exclude: bool = Field(default=False, validation_alias=AliasChoices("DeviceTypeExclude", "deviceTypeExclude"), serialization_alias="DeviceTypeExclude")
    device_source_concept: Optional[int] = Field(default=None, validation_alias=AliasChoices("DeviceSourceConcept", "deviceSourceConcept"), serialization_alias="DeviceSourceConcept")
    unique_device_id: Optional[TextFilter] = Field(default=None, validation_alias=AliasChoices("UniqueDeviceId", "uniqueDeviceId"), serialization_alias="UniqueDeviceId")
    quantity: Optional[NumericRange] = Field(default=None, validation_alias=AliasChoices("Quantity", "quantity"), serialization_alias="Quantity")
    age: Optional[NumericRange] = Field(default=None, validation_alias=AliasChoices("Age", "age"), serialization_alias="Age")
    gender: Optional[List[Concept]] = Field(default=None, validation_alias=AliasChoices("Gender", "gender"), serialization_alias="Gender")
    gender_cs: Optional[ConceptSetSelection] = Field(default=None, validation_alias=AliasChoices("GenderCS", "genderCS"), serialization_alias="GenderCS")
    provider_specialty: Optional[List[Concept]] = Field(default=None, validation_alias=AliasChoices("ProviderSpecialty", "providerSpecialty"), serialization_alias="ProviderSpecialty")
    provider_specialty_cs: Optional[ConceptSetSelection] = Field(default=None, validation_alias=AliasChoices("ProviderSpecialtyCS", "providerSpecialtyCS"), serialization_alias="ProviderSpecialtyCS")
    visit_type: Optional[List[Concept]] = Field(default=None, validation_alias=AliasChoices("VisitType", "visitType"), serialization_alias="VisitType")
    visit_type_cs: Optional[ConceptSetSelection] = Field(default=None, validation_alias=AliasChoices("VisitTypeCS", "visitTypeCS"), serialization_alias="VisitTypeCS")


class Specimen(Criteria):
    """Specimen criteria. Filters on specimen records in the OMOP CDM."""
    codeset_id: Optional[int] = Field(default=None, validation_alias=AliasChoices("CodesetId", "codesetId"), serialization_alias="CodesetId")
    first: Optional[bool] = Field(default=None, validation_alias=AliasChoices("First", "first"), serialization_alias="First")
    occurrence_start_date: Optional[DateRange] = Field(default=None, validation_alias=AliasChoices("OccurrenceStartDate", "occurrenceStartDate"), serialization_alias="OccurrenceStartDate")
    occurrence_end_date: Optional[DateRange] = Field(default=None, validation_alias=AliasChoices("OccurrenceEndDate", "occurrenceEndDate"), serialization_alias="OccurrenceEndDate")
    specimen_type: Optional[List[Concept]] = Field(default=None, validation_alias=AliasChoices("SpecimenType", "specimenType"), serialization_alias="SpecimenType")
    specimen_type_cs: Optional[ConceptSetSelection] = Field(default=None, validation_alias=AliasChoices("SpecimenTypeCS", "specimenTypeCS"), serialization_alias="SpecimenTypeCS")
    specimen_type_exclude: bool = Field(default=False, validation_alias=AliasChoices("SpecimenTypeExclude", "specimenTypeExclude"), serialization_alias="SpecimenTypeExclude")
    specimen_source_concept: Optional[int] = Field(default=None, validation_alias=AliasChoices("SpecimenSourceConcept", "specimenSourceConcept"), serialization_alias="SpecimenSourceConcept")
    quantity: Optional[NumericRange] = Field(default=None, validation_alias=AliasChoices("Quantity", "quantity"), serialization_alias="Quantity")
    unit: Optional[List[Concept]] = Field(default=None, validation_alias=AliasChoices("Unit", "unit"), serialization_alias="Unit")
    unit_cs: Optional[ConceptSetSelection] = Field(default=None, validation_alias=AliasChoices("UnitCS", "unitCS"), serialization_alias="UnitCS")
    anatomic_site: Optional[List[Concept]] = Field(default=None, validation_alias=AliasChoices("AnatomicSite", "anatomicSite"), serialization_alias="AnatomicSite")
    anatomic_site_cs: Optional[ConceptSetSelection] = Field(default=None, validation_alias=AliasChoices("AnatomicSiteCS", "anatomicSiteCS"), serialization_alias="AnatomicSiteCS")
    disease_status: Optional[List[Concept]] = Field(default=None, validation_alias=AliasChoices("DiseaseStatus", "diseaseStatus"), serialization_alias="DiseaseStatus")
    disease_status_cs: Optional[ConceptSetSelection] = Field(default=None, validation_alias=AliasChoices("DiseaseStatusCS", "diseaseStatusCS"), serialization_alias="DiseaseStatusCS")
    source_id: Optional[TextFilter] = Field(default=None, validation_alias=AliasChoices("SourceId", "sourceId"), serialization_alias="SourceId")
    age: Optional[NumericRange] = Field(default=None, validation_alias=AliasChoices("Age", "age"), serialization_alias="Age")
    gender: Optional[List[Concept]] = Field(default=None, validation_alias=AliasChoices("Gender", "gender"), serialization_alias="Gender")
    gender_cs: Optional[ConceptSetSelection] = Field(default=None, validation_alias=AliasChoices("GenderCS", "genderCS"), serialization_alias="GenderCS")


class Death(Criteria):
    """Death criteria. Filters on death records in the OMOP CDM."""
    codeset_id: Optional[int] = Field(default=None, validation_alias=AliasChoices("CodesetId", "codesetId"), serialization_alias="CodesetId")
    occurrence_start_date: Optional[DateRange] = Field(default=None, validation_alias=AliasChoices("OccurrenceStartDate", "occurrenceStartDate"), serialization_alias="OccurrenceStartDate")
    occurrence_end_date: Optional[DateRange] = Field(default=None, validation_alias=AliasChoices("OccurrenceEndDate", "occurrenceEndDate"), serialization_alias="OccurrenceEndDate")
    death_type: Optional[List[Concept]] = Field(default=None, validation_alias=AliasChoices("DeathType", "deathType"), serialization_alias="DeathType")
    death_type_cs: Optional[ConceptSetSelection] = Field(default=None, validation_alias=AliasChoices("DeathTypeCS", "deathTypeCS"), serialization_alias="DeathTypeCS")
    death_type_exclude: bool = Field(default=False, validation_alias=AliasChoices("DeathTypeExclude", "deathTypeExclude"), serialization_alias="DeathTypeExclude")
    death_source_concept: Optional[int] = Field(default=None, validation_alias=AliasChoices("DeathSourceConcept", "deathSourceConcept"), serialization_alias="DeathSourceConcept")
    cause_source_concept: Optional[int] = Field(default=None, validation_alias=AliasChoices("CauseSourceConcept", "causeSourceConcept"), serialization_alias="CauseSourceConcept")
    cause_source_concept_cs: Optional[ConceptSetSelection] = Field(default=None, validation_alias=AliasChoices("CauseSourceConceptCS", "causeSourceConceptCS"), serialization_alias="CauseSourceConceptCS")
    age: Optional[NumericRange] = Field(default=None, validation_alias=AliasChoices("Age", "age"), serialization_alias="Age")
    gender: Optional[List[Concept]] = Field(default=None, validation_alias=AliasChoices("Gender", "gender"), serialization_alias="Gender")
    gender_cs: Optional[ConceptSetSelection] = Field(default=None, validation_alias=AliasChoices("GenderCS", "genderCS"), serialization_alias="GenderCS")


class VisitDetail(Criteria):
    """Visit detail criteria. Filters on visit detail records in the OMOP CDM."""
    codeset_id: Optional[int] = Field(default=None, validation_alias=AliasChoices("CodesetId", "codesetId"), serialization_alias="CodesetId")
    first: Optional[bool] = Field(default=None, validation_alias=AliasChoices("First", "first"), serialization_alias="First")
    visit_detail_start_date: Optional[DateRange] = Field(default=None, validation_alias=AliasChoices("VisitDetailStartDate", "visitDetailStartDate"), serialization_alias="VisitDetailStartDate")
    visit_detail_end_date: Optional[DateRange] = Field(default=None, validation_alias=AliasChoices("VisitDetailEndDate", "visitDetailEndDate"), serialization_alias="VisitDetailEndDate")
    visit_detail_type: Optional[List[Concept]] = Field(default=None, validation_alias=AliasChoices("VisitDetailType", "visitDetailType"), serialization_alias="VisitDetailType")
    visit_detail_type_cs: Optional[ConceptSetSelection] = Field(default=None, validation_alias=AliasChoices("VisitDetailTypeCS", "visitDetailTypeCS"), serialization_alias="VisitDetailTypeCS")
    visit_detail_type_exclude: bool = Field(default=False, validation_alias=AliasChoices("VisitDetailTypeExclude", "visitDetailTypeExclude"), serialization_alias="VisitDetailTypeExclude")
    visit_detail_source_concept: Optional[int] = Field(default=None, validation_alias=AliasChoices("VisitDetailSourceConcept", "visitDetailSourceConcept"), serialization_alias="VisitDetailSourceConcept")
    visit_detail_length: Optional[NumericRange] = Field(default=None, validation_alias=AliasChoices("VisitDetailLength", "visitDetailLength"), serialization_alias="VisitDetailLength")
    age: Optional[NumericRange] = Field(default=None, validation_alias=AliasChoices("Age", "age"), serialization_alias="Age")
    gender: Optional[List[Concept]] = Field(default=None, validation_alias=AliasChoices("Gender", "gender"), serialization_alias="Gender")
    gender_cs: Optional[ConceptSetSelection] = Field(default=None, validation_alias=AliasChoices("GenderCS", "genderCS"), serialization_alias="GenderCS")
    provider_specialty: Optional[List[Concept]] = Field(default=None, validation_alias=AliasChoices("ProviderSpecialty", "providerSpecialty"), serialization_alias="ProviderSpecialty")
    provider_specialty_cs: Optional[ConceptSetSelection] = Field(default=None, validation_alias=AliasChoices("ProviderSpecialtyCS", "providerSpecialtyCS"), serialization_alias="ProviderSpecialtyCS")
    place_of_service: Optional[List[Concept]] = Field(default=None, validation_alias=AliasChoices("PlaceOfService", "placeOfService"), serialization_alias="PlaceOfService")
    place_of_service_cs: Optional[ConceptSetSelection] = Field(default=None, validation_alias=AliasChoices("PlaceOfServiceCS", "placeOfServiceCS"), serialization_alias="PlaceOfServiceCS")
    place_of_service_location: Optional[int] = Field(default=None, validation_alias=AliasChoices("PlaceOfServiceLocation", "placeOfServiceLocation"), serialization_alias="PlaceOfServiceLocation")
    discharge_to: Optional[List[Concept]] = Field(default=None, validation_alias=AliasChoices("DischargeTo", "dischargeTo"), serialization_alias="DischargeTo")
    discharge_to_cs: Optional[ConceptSetSelection] = Field(default=None, validation_alias=AliasChoices("DischargeToCS", "dischargeToCS"), serialization_alias="DischargeToCS")


class ObservationPeriod(Criteria):
    """Observation period criteria. Filters on observation period records in the OMOP CDM."""
    first: Optional[bool] = Field(default=None, validation_alias=AliasChoices("First", "first"), serialization_alias="First")
    period_start_date: Optional[DateRange] = Field(default=None, validation_alias=AliasChoices("PeriodStartDate", "periodStartDate"), serialization_alias="PeriodStartDate")
    period_end_date: Optional[DateRange] = Field(default=None, validation_alias=AliasChoices("PeriodEndDate", "periodEndDate"), serialization_alias="PeriodEndDate")
    period_type: Optional[List[Concept]] = Field(default=None, validation_alias=AliasChoices("PeriodType", "periodType"), serialization_alias="PeriodType")
    period_type_cs: Optional[ConceptSetSelection] = Field(default=None, validation_alias=AliasChoices("PeriodTypeCS", "periodTypeCS"), serialization_alias="PeriodTypeCS")
    user_defined_period: Optional["Period"] = Field(default=None, validation_alias=AliasChoices("UserDefinedPeriod", "userDefinedPeriod"), serialization_alias="UserDefinedPeriod")
    period_length: Optional[NumericRange] = Field(default=None, validation_alias=AliasChoices("PeriodLength", "periodLength"), serialization_alias="PeriodLength")
    age_at_start: Optional[NumericRange] = Field(default=None, validation_alias=AliasChoices("AgeAtStart", "ageAtStart"), serialization_alias="AgeAtStart")
    age_at_end: Optional[NumericRange] = Field(default=None, validation_alias=AliasChoices("AgeAtEnd", "ageAtEnd"), serialization_alias="AgeAtEnd")


class PayerPlanPeriod(Criteria):
    """Payer plan period criteria. Filters on payer plan period records in the OMOP CDM."""
    first: Optional[bool] = Field(default=None, validation_alias=AliasChoices("First", "first"), serialization_alias="First")
    period_start_date: Optional[DateRange] = Field(default=None, validation_alias=AliasChoices("PeriodStartDate", "periodStartDate"), serialization_alias="PeriodStartDate")
    period_end_date: Optional[DateRange] = Field(default=None, validation_alias=AliasChoices("PeriodEndDate", "periodEndDate"), serialization_alias="PeriodEndDate")
    user_defined_period: Optional[Period] = Field(default=None, validation_alias=AliasChoices("UserDefinedPeriod", "userDefinedPeriod"), serialization_alias="UserDefinedPeriod")
    period_length: Optional[NumericRange] = Field(default=None, validation_alias=AliasChoices("PeriodLength", "periodLength"), serialization_alias="PeriodLength")
    age_at_start: Optional[NumericRange] = Field(default=None, validation_alias=AliasChoices("AgeAtStart", "ageAtStart"), serialization_alias="AgeAtStart")
    age_at_end: Optional[NumericRange] = Field(default=None, validation_alias=AliasChoices("AgeAtEnd", "ageAtEnd"), serialization_alias="AgeAtEnd")
    gender: Optional[List[Concept]] = Field(default=None, validation_alias=AliasChoices("Gender", "gender"), serialization_alias="Gender")
    gender_cs: Optional[ConceptSetSelection] = Field(default=None, validation_alias=AliasChoices("GenderCS", "genderCS"), serialization_alias="GenderCS")
    payer_concept: Optional[int] = Field(default=None, validation_alias=AliasChoices("PayerConcept", "payerConcept"), serialization_alias="PayerConcept")
    plan_concept: Optional[int] = Field(default=None, validation_alias=AliasChoices("PlanConcept", "planConcept"), serialization_alias="PlanConcept")
    sponsor_concept: Optional[int] = Field(default=None, validation_alias=AliasChoices("SponsorConcept", "sponsorConcept"), serialization_alias="SponsorConcept")
    stop_reason_concept: Optional[int] = Field(default=None, validation_alias=AliasChoices("StopReasonConcept", "stopReasonConcept"), serialization_alias="StopReasonConcept")
    payer_source_concept: Optional[int] = Field(default=None, validation_alias=AliasChoices("PayerSourceConcept", "payerSourceConcept"), serialization_alias="PayerSourceConcept")
    plan_source_concept: Optional[int] = Field(default=None, validation_alias=AliasChoices("PlanSourceConcept", "planSourceConcept"), serialization_alias="PlanSourceConcept")
    sponsor_source_concept: Optional[int] = Field(default=None, validation_alias=AliasChoices("SponsorSourceConcept", "sponsorSourceConcept"), serialization_alias="SponsorSourceConcept")
    stop_reason_source_concept: Optional[int] = Field(default=None, validation_alias=AliasChoices("StopReasonSourceConcept", "stopReasonSourceConcept"), serialization_alias="StopReasonSourceConcept")


class LocationRegion(Criteria):
    """Location region criteria. Filters on geographic location/region in the OMOP CDM."""
    codeset_id: Optional[int] = Field(default=None, validation_alias=AliasChoices("CodesetId", "codesetId"), serialization_alias="CodesetId")


class ConditionEra(Criteria):
    """Condition era criteria. Filters on condition era records in the OMOP CDM."""
    codeset_id: Optional[int] = Field(default=None, validation_alias=AliasChoices("CodesetId", "codesetId"), serialization_alias="CodesetId")
    first: Optional[bool] = Field(default=None, validation_alias=AliasChoices("First", "first"), serialization_alias="First")
    era_start_date: Optional[DateRange] = Field(default=None, validation_alias=AliasChoices("EraStartDate", "eraStartDate"), serialization_alias="EraStartDate")
    era_end_date: Optional[DateRange] = Field(default=None, validation_alias=AliasChoices("EraEndDate", "eraEndDate"), serialization_alias="EraEndDate")
    occurrence_count: Optional[NumericRange] = Field(default=None, validation_alias=AliasChoices("OccurrenceCount", "occurrenceCount"), serialization_alias="OccurrenceCount")
    era_length: Optional[NumericRange] = Field(default=None, validation_alias=AliasChoices("EraLength", "eraLength"), serialization_alias="EraLength")
    age_at_start: Optional[NumericRange] = Field(default=None, validation_alias=AliasChoices("AgeAtStart", "ageAtStart"), serialization_alias="AgeAtStart")
    age_at_end: Optional[NumericRange] = Field(default=None, validation_alias=AliasChoices("AgeAtEnd", "ageAtEnd"), serialization_alias="AgeAtEnd")
    gender: Optional[List[Concept]] = Field(default=None, validation_alias=AliasChoices("Gender", "gender"), serialization_alias="Gender")
    gender_cs: Optional[ConceptSetSelection] = Field(default=None, validation_alias=AliasChoices("GenderCS", "genderCS"), serialization_alias="GenderCS")


class DrugEra(Criteria):
    """Drug era criteria. Filters on drug era records in the OMOP CDM."""
    codeset_id: Optional[int] = Field(default=None, validation_alias=AliasChoices("CodesetId", "codesetId"), serialization_alias="CodesetId")
    first: Optional[bool] = Field(default=None, validation_alias=AliasChoices("First", "first"), serialization_alias="First")
    era_start_date: Optional[DateRange] = Field(default=None, validation_alias=AliasChoices("EraStartDate", "eraStartDate"), serialization_alias="EraStartDate")
    era_end_date: Optional[DateRange] = Field(default=None, validation_alias=AliasChoices("EraEndDate", "eraEndDate"), serialization_alias="EraEndDate")
    occurrence_count: Optional[NumericRange] = Field(default=None, validation_alias=AliasChoices("OccurrenceCount", "occurrenceCount"), serialization_alias="OccurrenceCount")
    gap_days: Optional[NumericRange] = Field(default=None, validation_alias=AliasChoices("GapDays", "gapDays"), serialization_alias="GapDays")
    era_length: Optional[NumericRange] = Field(default=None, validation_alias=AliasChoices("EraLength", "eraLength"), serialization_alias="EraLength")
    age_at_start: Optional[NumericRange] = Field(default=None, validation_alias=AliasChoices("AgeAtStart", "ageAtStart"), serialization_alias="AgeAtStart")
    age_at_end: Optional[NumericRange] = Field(default=None, validation_alias=AliasChoices("AgeAtEnd", "ageAtEnd"), serialization_alias="AgeAtEnd")
    gender: Optional[List[Concept]] = Field(default=None, validation_alias=AliasChoices("Gender", "gender"), serialization_alias="Gender")
    gender_cs: Optional[ConceptSetSelection] = Field(default=None, validation_alias=AliasChoices("GenderCS", "genderCS"), serialization_alias="GenderCS")


class DoseEra(Criteria):
    """Dose era criteria. Filters on dose era records in the OMOP CDM."""
    codeset_id: Optional[int] = Field(default=None, validation_alias=AliasChoices("CodesetId", "codesetId"), serialization_alias="CodesetId")
    first: Optional[bool] = Field(default=None, validation_alias=AliasChoices("First", "first"), serialization_alias="First")
    era_start_date: Optional[DateRange] = Field(default=None, validation_alias=AliasChoices("EraStartDate", "eraStartDate"), serialization_alias="EraStartDate")
    era_end_date: Optional[DateRange] = Field(default=None, validation_alias=AliasChoices("EraEndDate", "eraEndDate"), serialization_alias="EraEndDate")
    unit: Optional[List[Concept]] = Field(default=None, validation_alias=AliasChoices("Unit", "unit"), serialization_alias="Unit")
    unit_cs: Optional[ConceptSetSelection] = Field(default=None, validation_alias=AliasChoices("UnitCS", "unitCS"), serialization_alias="UnitCS")
    dose_value: Optional[NumericRange] = Field(default=None, validation_alias=AliasChoices("DoseValue", "doseValue"), serialization_alias="DoseValue")
    era_length: Optional[NumericRange] = Field(default=None, validation_alias=AliasChoices("EraLength", "eraLength"), serialization_alias="EraLength")
    age_at_start: Optional[NumericRange] = Field(default=None, validation_alias=AliasChoices("AgeAtStart", "ageAtStart"), serialization_alias="AgeAtStart")
    age_at_end: Optional[NumericRange] = Field(default=None, validation_alias=AliasChoices("AgeAtEnd", "ageAtEnd"), serialization_alias="AgeAtEnd")
    gender: Optional[List[Concept]] = Field(default=None, validation_alias=AliasChoices("Gender", "gender"), serialization_alias="Gender")
    gender_cs: Optional[ConceptSetSelection] = Field(default=None, validation_alias=AliasChoices("GenderCS", "genderCS"), serialization_alias="GenderCS")


_criteria_all = [
    ConditionOccurrence, DrugExposure, ProcedureOccurrence,
    VisitOccurrence, Observation, Measurement,
    DeviceExposure, Specimen, Death, VisitDetail,
    ObservationPeriod, PayerPlanPeriod, LocationRegion,
    ConditionEra, DrugEra, DoseEra,
]
try:
    from .groups import CriteriaGroup
    for _c in _criteria_all:
        _c.model_rebuild()
except ImportError:
    pass
