package org.ohdsi.circe.schema.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

import java.util.List;

import static org.ohdsi.circe.schema.model.Groups.CriteriaGroup;
import static org.ohdsi.circe.schema.model.Vocabulary.Concept;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
@JsonSubTypes({
    @Type(value = ConditionOccurrence.class, name = "ConditionOccurrence"),
    @Type(value = DrugExposure.class, name = "DrugExposure"),
    @Type(value = ProcedureOccurrence.class, name = "ProcedureOccurrence"),
    @Type(value = VisitOccurrence.class, name = "VisitOccurrence"),
    @Type(value = Observation.class, name = "Observation"),
    @Type(value = Measurement.class, name = "Measurement"),
    @Type(value = DeviceExposure.class, name = "DeviceExposure"),
    @Type(value = Specimen.class, name = "Specimen"),
    @Type(value = Death.class, name = "Death"),
    @Type(value = VisitDetail.class, name = "VisitDetail"),
    @Type(value = ObservationPeriod.class, name = "ObservationPeriod"),
    @Type(value = PayerPlanPeriod.class, name = "PayerPlanPeriod"),
    @Type(value = LocationRegion.class, name = "LocationRegion"),
    @Type(value = ConditionEra.class, name = "ConditionEra"),
    @Type(value = DrugEra.class, name = "DrugEra"),
    @Type(value = DoseEra.class, name = "DoseEra")
})
public abstract class Criteria {
    @JsonProperty("DateAdjustment")
    public CoreTypes.DateAdjustment dateAdjustment;
    @JsonProperty("CorrelatedCriteria")
    public CriteriaGroup correlatedCriteria;
}

@JsonTypeName("ConditionOccurrence")
class ConditionOccurrence extends Criteria {
    @JsonProperty("CodesetId") public Integer codesetId;
    @JsonProperty("First") public Boolean first;
    @JsonProperty("OccurrenceStartDate") public CoreTypes.DateRange occurrenceStartDate;
    @JsonProperty("OccurrenceEndDate") public CoreTypes.DateRange occurrenceEndDate;
    @JsonProperty("ConditionType") public List<Concept> conditionType;
    @JsonProperty("ConditionTypeCS") public CoreTypes.ConceptSetSelection conditionTypeCS;
    @JsonProperty("ConditionTypeExclude") public boolean conditionTypeExclude;
    @JsonProperty("StopReason") public CoreTypes.TextFilter stopReason;
    @JsonProperty("ConditionSourceConcept") public Integer conditionSourceConcept;
    @JsonProperty("Age") public CoreTypes.NumericRange age;
    @JsonProperty("Gender") public List<Concept> gender;
    @JsonProperty("GenderCS") public CoreTypes.ConceptSetSelection genderCS;
    @JsonProperty("ProviderSpecialty") public List<Concept> providerSpecialty;
    @JsonProperty("ProviderSpecialtyCS") public CoreTypes.ConceptSetSelection providerSpecialtyCS;
    @JsonProperty("VisitType") public List<Concept> visitType;
    @JsonProperty("VisitTypeCS") public CoreTypes.ConceptSetSelection visitTypeCS;
    @JsonProperty("ConditionStatus") public List<Concept> conditionStatus;
    @JsonProperty("ConditionStatusCS") public CoreTypes.ConceptSetSelection conditionStatusCS;
}

@JsonTypeName("DrugExposure")
class DrugExposure extends Criteria {
    @JsonProperty("CodesetId") public Integer codesetId;
    @JsonProperty("First") public Boolean first;
    @JsonProperty("OccurrenceStartDate") public CoreTypes.DateRange occurrenceStartDate;
    @JsonProperty("OccurrenceEndDate") public CoreTypes.DateRange occurrenceEndDate;
    @JsonProperty("DrugType") public List<Concept> drugType;
    @JsonProperty("DrugTypeCS") public CoreTypes.ConceptSetSelection drugTypeCS;
    @JsonProperty("DrugTypeExclude") public boolean drugTypeExclude;
    @JsonProperty("DrugSourceConcept") public Integer drugSourceConcept;
    @JsonProperty("StopReason") public CoreTypes.TextFilter stopReason;
    @JsonProperty("Age") public CoreTypes.NumericRange age;
    @JsonProperty("Gender") public List<Concept> gender;
    @JsonProperty("GenderCS") public CoreTypes.ConceptSetSelection genderCS;
    @JsonProperty("ProviderSpecialty") public List<Concept> providerSpecialty;
    @JsonProperty("ProviderSpecialtyCS") public CoreTypes.ConceptSetSelection providerSpecialtyCS;
    @JsonProperty("VisitType") public List<Concept> visitType;
    @JsonProperty("VisitTypeCS") public CoreTypes.ConceptSetSelection visitTypeCS;
    @JsonProperty("RouteConcept") public List<Concept> routeConcept;
    @JsonProperty("RouteConceptCS") public CoreTypes.ConceptSetSelection routeConceptCS;
    @JsonProperty("Quantity") public CoreTypes.NumericRange quantity;
    @JsonProperty("DaysSupply") public CoreTypes.NumericRange daysSupply;
    @JsonProperty("Refills") public CoreTypes.NumericRange refills;
    @JsonProperty("EffectiveDrugDose") public CoreTypes.NumericRange effectiveDrugDose;
    @JsonProperty("DoseUnit") public List<Concept> doseUnit;
    @JsonProperty("DoseUnitCS") public CoreTypes.ConceptSetSelection doseUnitCS;
    @JsonProperty("LotNumber") public CoreTypes.TextFilter lotNumber;
}

@JsonTypeName("ProcedureOccurrence")
class ProcedureOccurrence extends Criteria {
    @JsonProperty("CodesetId") public Integer codesetId;
    @JsonProperty("First") public Boolean first;
    @JsonProperty("OccurrenceStartDate") public CoreTypes.DateRange occurrenceStartDate;
    @JsonProperty("OccurrenceEndDate") public CoreTypes.DateRange occurrenceEndDate;
    @JsonProperty("ProcedureType") public List<Concept> procedureType;
    @JsonProperty("ProcedureTypeCS") public CoreTypes.ConceptSetSelection procedureTypeCS;
    @JsonProperty("ProcedureTypeExclude") public boolean procedureTypeExclude;
    @JsonProperty("ProcedureSourceConcept") public Integer procedureSourceConcept;
    @JsonProperty("Quantity") public CoreTypes.NumericRange quantity;
    @JsonProperty("Age") public CoreTypes.NumericRange age;
    @JsonProperty("Gender") public List<Concept> gender;
    @JsonProperty("GenderCS") public CoreTypes.ConceptSetSelection genderCS;
    @JsonProperty("ProviderSpecialty") public List<Concept> providerSpecialty;
    @JsonProperty("ProviderSpecialtyCS") public CoreTypes.ConceptSetSelection providerSpecialtyCS;
    @JsonProperty("VisitType") public List<Concept> visitType;
    @JsonProperty("VisitTypeCS") public CoreTypes.ConceptSetSelection visitTypeCS;
    @JsonProperty("Modifier") public List<Concept> modifier;
    @JsonProperty("ModifierCS") public CoreTypes.ConceptSetSelection modifierCS;
}

@JsonTypeName("VisitOccurrence")
class VisitOccurrence extends Criteria {
    @JsonProperty("CodesetId") public Integer codesetId;
    @JsonProperty("First") public Boolean first;
    @JsonProperty("OccurrenceStartDate") public CoreTypes.DateRange occurrenceStartDate;
    @JsonProperty("OccurrenceEndDate") public CoreTypes.DateRange occurrenceEndDate;
    @JsonProperty("VisitType") public List<Concept> visitType;
    @JsonProperty("VisitTypeCS") public CoreTypes.ConceptSetSelection visitTypeCS;
    @JsonProperty("VisitTypeExclude") public boolean visitTypeExclude;
    @JsonProperty("VisitSourceConcept") public Integer visitSourceConcept;
    @JsonProperty("VisitLength") public CoreTypes.NumericRange visitLength;
    @JsonProperty("Age") public CoreTypes.NumericRange age;
    @JsonProperty("Gender") public List<Concept> gender;
    @JsonProperty("GenderCS") public CoreTypes.ConceptSetSelection genderCS;
    @JsonProperty("ProviderSpecialty") public List<Concept> providerSpecialty;
    @JsonProperty("ProviderSpecialtyCS") public CoreTypes.ConceptSetSelection providerSpecialtyCS;
    @JsonProperty("PlaceOfService") public List<Concept> placeOfService;
    @JsonProperty("PlaceOfServiceCS") public CoreTypes.ConceptSetSelection placeOfServiceCS;
    @JsonProperty("PlaceOfServiceLocation") public Integer placeOfServiceLocation;
}

@JsonTypeName("Observation")
class Observation extends Criteria {
    @JsonProperty("CodesetId") public Integer codesetId;
    @JsonProperty("First") public Boolean first;
    @JsonProperty("OccurrenceStartDate") public CoreTypes.DateRange occurrenceStartDate;
    @JsonProperty("OccurrenceEndDate") public CoreTypes.DateRange occurrenceEndDate;
    @JsonProperty("ObservationType") public List<Concept> observationType;
    @JsonProperty("ObservationTypeCS") public CoreTypes.ConceptSetSelection observationTypeCS;
    @JsonProperty("ObservationTypeExclude") public boolean observationTypeExclude;
    @JsonProperty("ObservationSourceConcept") public Integer observationSourceConcept;
    @JsonProperty("ValueAsNumber") public CoreTypes.NumericRange valueAsNumber;
    @JsonProperty("ValueAsString") public CoreTypes.TextFilter valueAsString;
    @JsonProperty("ValueAsConcept") public List<Concept> valueAsConcept;
    @JsonProperty("ValueAsConceptCS") public CoreTypes.ConceptSetSelection valueAsConceptCS;
    @JsonProperty("Unit") public List<Concept> unit;
    @JsonProperty("UnitCS") public CoreTypes.ConceptSetSelection unitCS;
    @JsonProperty("Qualifier") public List<Concept> qualifier;
    @JsonProperty("QualifierCS") public CoreTypes.ConceptSetSelection qualifierCS;
    @JsonProperty("Age") public CoreTypes.NumericRange age;
    @JsonProperty("Gender") public List<Concept> gender;
    @JsonProperty("GenderCS") public CoreTypes.ConceptSetSelection genderCS;
    @JsonProperty("ProviderSpecialty") public List<Concept> providerSpecialty;
    @JsonProperty("ProviderSpecialtyCS") public CoreTypes.ConceptSetSelection providerSpecialtyCS;
    @JsonProperty("VisitType") public List<Concept> visitType;
    @JsonProperty("VisitTypeCS") public CoreTypes.ConceptSetSelection visitTypeCS;
}

@JsonTypeName("Measurement")
class Measurement extends Criteria {
    @JsonProperty("CodesetId") public Integer codesetId;
    @JsonProperty("First") public Boolean first;
    @JsonProperty("OccurrenceStartDate") public CoreTypes.DateRange occurrenceStartDate;
    @JsonProperty("OccurrenceEndDate") public CoreTypes.DateRange occurrenceEndDate;
    @JsonProperty("MeasurementType") public List<Concept> measurementType;
    @JsonProperty("MeasurementTypeCS") public CoreTypes.ConceptSetSelection measurementTypeCS;
    @JsonProperty("MeasurementTypeExclude") public boolean measurementTypeExclude;
    @JsonProperty("MeasurementSourceConcept") public Integer measurementSourceConcept;
    @JsonProperty("ValueAsNumber") public CoreTypes.NumericRange valueAsNumber;
    @JsonProperty("ValueAsString") public CoreTypes.TextFilter valueAsString;
    @JsonProperty("ValueAsConcept") public List<Concept> valueAsConcept;
    @JsonProperty("ValueAsConceptCS") public CoreTypes.ConceptSetSelection valueAsConceptCS;
    @JsonProperty("Operator") public List<Concept> operator;
    @JsonProperty("OperatorCS") public CoreTypes.ConceptSetSelection operatorCS;
    @JsonProperty("Unit") public List<Concept> unit;
    @JsonProperty("UnitCS") public CoreTypes.ConceptSetSelection unitCS;
    @JsonProperty("RangeLow") public CoreTypes.NumericRange rangeLow;
    @JsonProperty("RangeHigh") public CoreTypes.NumericRange rangeHigh;
    @JsonProperty("RangeLowRatio") public CoreTypes.NumericRange rangeLowRatio;
    @JsonProperty("RangeHighRatio") public CoreTypes.NumericRange rangeHighRatio;
    @JsonProperty("Abnormal") public Boolean abnormal;
    @JsonProperty("Age") public CoreTypes.NumericRange age;
    @JsonProperty("Gender") public List<Concept> gender;
    @JsonProperty("GenderCS") public CoreTypes.ConceptSetSelection genderCS;
    @JsonProperty("ProviderSpecialty") public List<Concept> providerSpecialty;
    @JsonProperty("ProviderSpecialtyCS") public CoreTypes.ConceptSetSelection providerSpecialtyCS;
    @JsonProperty("VisitType") public List<Concept> visitType;
    @JsonProperty("VisitTypeCS") public CoreTypes.ConceptSetSelection visitTypeCS;
}

@JsonTypeName("DeviceExposure")
class DeviceExposure extends Criteria {
    @JsonProperty("CodesetId") public Integer codesetId;
    @JsonProperty("First") public Boolean first;
    @JsonProperty("OccurrenceStartDate") public CoreTypes.DateRange occurrenceStartDate;
    @JsonProperty("OccurrenceEndDate") public CoreTypes.DateRange occurrenceEndDate;
    @JsonProperty("DeviceType") public List<Concept> deviceType;
    @JsonProperty("DeviceTypeCS") public CoreTypes.ConceptSetSelection deviceTypeCS;
    @JsonProperty("DeviceTypeExclude") public boolean deviceTypeExclude;
    @JsonProperty("DeviceSourceConcept") public Integer deviceSourceConcept;
    @JsonProperty("UniqueDeviceId") public CoreTypes.TextFilter uniqueDeviceId;
    @JsonProperty("Quantity") public CoreTypes.NumericRange quantity;
    @JsonProperty("Age") public CoreTypes.NumericRange age;
    @JsonProperty("Gender") public List<Concept> gender;
    @JsonProperty("GenderCS") public CoreTypes.ConceptSetSelection genderCS;
    @JsonProperty("ProviderSpecialty") public List<Concept> providerSpecialty;
    @JsonProperty("ProviderSpecialtyCS") public CoreTypes.ConceptSetSelection providerSpecialtyCS;
    @JsonProperty("VisitType") public List<Concept> visitType;
    @JsonProperty("VisitTypeCS") public CoreTypes.ConceptSetSelection visitTypeCS;
}

@JsonTypeName("Specimen")
class Specimen extends Criteria {
    @JsonProperty("CodesetId") public Integer codesetId;
    @JsonProperty("First") public Boolean first;
    @JsonProperty("OccurrenceStartDate") public CoreTypes.DateRange occurrenceStartDate;
    @JsonProperty("OccurrenceEndDate") public CoreTypes.DateRange occurrenceEndDate;
    @JsonProperty("SpecimenType") public List<Concept> specimenType;
    @JsonProperty("SpecimenTypeCS") public CoreTypes.ConceptSetSelection specimenTypeCS;
    @JsonProperty("SpecimenTypeExclude") public boolean specimenTypeExclude;
    @JsonProperty("SpecimenSourceConcept") public Integer specimenSourceConcept;
    @JsonProperty("Quantity") public CoreTypes.NumericRange quantity;
    @JsonProperty("Unit") public List<Concept> unit;
    @JsonProperty("UnitCS") public CoreTypes.ConceptSetSelection unitCS;
    @JsonProperty("AnatomicSite") public List<Concept> anatomicSite;
    @JsonProperty("AnatomicSiteCS") public CoreTypes.ConceptSetSelection anatomicSiteCS;
    @JsonProperty("DiseaseStatus") public List<Concept> diseaseStatus;
    @JsonProperty("DiseaseStatusCS") public CoreTypes.ConceptSetSelection diseaseStatusCS;
    @JsonProperty("SourceId") public CoreTypes.TextFilter sourceId;
    @JsonProperty("Age") public CoreTypes.NumericRange age;
    @JsonProperty("Gender") public List<Concept> gender;
    @JsonProperty("GenderCS") public CoreTypes.ConceptSetSelection genderCS;
}

@JsonTypeName("Death")
class Death extends Criteria {
    @JsonProperty("CodesetId") public Integer codesetId;
    @JsonProperty("OccurrenceStartDate") public CoreTypes.DateRange occurrenceStartDate;
    @JsonProperty("OccurrenceEndDate") public CoreTypes.DateRange occurrenceEndDate;
    @JsonProperty("DeathType") public List<Concept> deathType;
    @JsonProperty("DeathTypeCS") public CoreTypes.ConceptSetSelection deathTypeCS;
    @JsonProperty("DeathTypeExclude") public boolean deathTypeExclude;
    @JsonProperty("DeathSourceConcept") public Integer deathSourceConcept;
    @JsonProperty("CauseSourceConcept") public Integer causeSourceConcept;
    @JsonProperty("CauseSourceConceptCS") public CoreTypes.ConceptSetSelection causeSourceConceptCS;
    @JsonProperty("Age") public CoreTypes.NumericRange age;
    @JsonProperty("Gender") public List<Concept> gender;
    @JsonProperty("GenderCS") public CoreTypes.ConceptSetSelection genderCS;
}

@JsonTypeName("VisitDetail")
class VisitDetail extends Criteria {
    @JsonProperty("CodesetId") public Integer codesetId;
    @JsonProperty("First") public Boolean first;
    @JsonProperty("VisitDetailStartDate") public CoreTypes.DateRange visitDetailStartDate;
    @JsonProperty("VisitDetailEndDate") public CoreTypes.DateRange visitDetailEndDate;
    @JsonProperty("VisitDetailType") public List<Concept> visitDetailType;
    @JsonProperty("VisitDetailTypeCS") public CoreTypes.ConceptSetSelection visitDetailTypeCS;
    @JsonProperty("VisitDetailTypeExclude") public boolean visitDetailTypeExclude;
    @JsonProperty("VisitDetailSourceConcept") public Integer visitDetailSourceConcept;
    @JsonProperty("VisitDetailLength") public CoreTypes.NumericRange visitDetailLength;
    @JsonProperty("Age") public CoreTypes.NumericRange age;
    @JsonProperty("Gender") public List<Concept> gender;
    @JsonProperty("GenderCS") public CoreTypes.ConceptSetSelection genderCS;
    @JsonProperty("ProviderSpecialty") public List<Concept> providerSpecialty;
    @JsonProperty("ProviderSpecialtyCS") public CoreTypes.ConceptSetSelection providerSpecialtyCS;
    @JsonProperty("PlaceOfService") public List<Concept> placeOfService;
    @JsonProperty("PlaceOfServiceCS") public CoreTypes.ConceptSetSelection placeOfServiceCS;
    @JsonProperty("PlaceOfServiceLocation") public Integer placeOfServiceLocation;
    @JsonProperty("DischargeTo") public List<Concept> dischargeTo;
    @JsonProperty("DischargeToCS") public CoreTypes.ConceptSetSelection dischargeToCS;
}

@JsonTypeName("ObservationPeriod")
class ObservationPeriod extends Criteria {
    @JsonProperty("First") public Boolean first;
    @JsonProperty("PeriodStartDate") public CoreTypes.DateRange periodStartDate;
    @JsonProperty("PeriodEndDate") public CoreTypes.DateRange periodEndDate;
    @JsonProperty("PeriodType") public List<Concept> periodType;
    @JsonProperty("PeriodTypeCS") public CoreTypes.ConceptSetSelection periodTypeCS;
    @JsonProperty("UserDefinedPeriod") public CoreTypes.Period userDefinedPeriod;
    @JsonProperty("PeriodLength") public CoreTypes.NumericRange periodLength;
    @JsonProperty("AgeAtStart") public CoreTypes.NumericRange ageAtStart;
    @JsonProperty("AgeAtEnd") public CoreTypes.NumericRange ageAtEnd;
}

@JsonTypeName("PayerPlanPeriod")
class PayerPlanPeriod extends Criteria {
    @JsonProperty("First") public Boolean first;
    @JsonProperty("PeriodStartDate") public CoreTypes.DateRange periodStartDate;
    @JsonProperty("PeriodEndDate") public CoreTypes.DateRange periodEndDate;
    @JsonProperty("UserDefinedPeriod") public CoreTypes.Period userDefinedPeriod;
    @JsonProperty("PeriodLength") public CoreTypes.NumericRange periodLength;
    @JsonProperty("AgeAtStart") public CoreTypes.NumericRange ageAtStart;
    @JsonProperty("AgeAtEnd") public CoreTypes.NumericRange ageAtEnd;
    @JsonProperty("Gender") public List<Concept> gender;
    @JsonProperty("GenderCS") public CoreTypes.ConceptSetSelection genderCS;
    @JsonProperty("PayerConcept") public Integer payerConcept;
    @JsonProperty("PlanConcept") public Integer planConcept;
    @JsonProperty("SponsorConcept") public Integer sponsorConcept;
    @JsonProperty("StopReasonConcept") public Integer stopReasonConcept;
    @JsonProperty("PayerSourceConcept") public Integer payerSourceConcept;
    @JsonProperty("PlanSourceConcept") public Integer planSourceConcept;
    @JsonProperty("SponsorSourceConcept") public Integer sponsorSourceConcept;
    @JsonProperty("StopReasonSourceConcept") public Integer stopReasonSourceConcept;
}

@JsonTypeName("LocationRegion")
class LocationRegion extends Criteria {
    @JsonProperty("CodesetId") public Integer codesetId;
}

@JsonTypeName("ConditionEra")
class ConditionEra extends Criteria {
    @JsonProperty("CodesetId") public Integer codesetId;
    @JsonProperty("First") public Boolean first;
    @JsonProperty("EraStartDate") public CoreTypes.DateRange eraStartDate;
    @JsonProperty("EraEndDate") public CoreTypes.DateRange eraEndDate;
    @JsonProperty("OccurrenceCount") public CoreTypes.NumericRange occurrenceCount;
    @JsonProperty("EraLength") public CoreTypes.NumericRange eraLength;
    @JsonProperty("AgeAtStart") public CoreTypes.NumericRange ageAtStart;
    @JsonProperty("AgeAtEnd") public CoreTypes.NumericRange ageAtEnd;
    @JsonProperty("Gender") public List<Concept> gender;
    @JsonProperty("GenderCS") public CoreTypes.ConceptSetSelection genderCS;
}

@JsonTypeName("DrugEra")
class DrugEra extends Criteria {
    @JsonProperty("CodesetId") public Integer codesetId;
    @JsonProperty("First") public Boolean first;
    @JsonProperty("EraStartDate") public CoreTypes.DateRange eraStartDate;
    @JsonProperty("EraEndDate") public CoreTypes.DateRange eraEndDate;
    @JsonProperty("OccurrenceCount") public CoreTypes.NumericRange occurrenceCount;
    @JsonProperty("GapDays") public CoreTypes.NumericRange gapDays;
    @JsonProperty("EraLength") public CoreTypes.NumericRange eraLength;
    @JsonProperty("AgeAtStart") public CoreTypes.NumericRange ageAtStart;
    @JsonProperty("AgeAtEnd") public CoreTypes.NumericRange ageAtEnd;
    @JsonProperty("Gender") public List<Concept> gender;
    @JsonProperty("GenderCS") public CoreTypes.ConceptSetSelection genderCS;
}

@JsonTypeName("DoseEra")
class DoseEra extends Criteria {
    @JsonProperty("CodesetId") public Integer codesetId;
    @JsonProperty("First") public Boolean first;
    @JsonProperty("EraStartDate") public CoreTypes.DateRange eraStartDate;
    @JsonProperty("EraEndDate") public CoreTypes.DateRange eraEndDate;
    @JsonProperty("Unit") public List<Concept> unit;
    @JsonProperty("UnitCS") public CoreTypes.ConceptSetSelection unitCS;
    @JsonProperty("DoseValue") public CoreTypes.NumericRange doseValue;
    @JsonProperty("EraLength") public CoreTypes.NumericRange eraLength;
    @JsonProperty("AgeAtStart") public CoreTypes.NumericRange ageAtStart;
    @JsonProperty("AgeAtEnd") public CoreTypes.NumericRange ageAtEnd;
    @JsonProperty("Gender") public List<Concept> gender;
    @JsonProperty("GenderCS") public CoreTypes.ConceptSetSelection genderCS;
}
