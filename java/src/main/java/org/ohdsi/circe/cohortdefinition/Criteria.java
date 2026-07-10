package org.ohdsi.circe.cohortdefinition;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = As.WRAPPER_OBJECT)
@JsonSubTypes({
    @JsonSubTypes.Type(value = ConditionEra.class, name = "ConditionEra"),
    @JsonSubTypes.Type(value = ConditionOccurrence.class, name = "ConditionOccurrence"),
    @JsonSubTypes.Type(value = Death.class, name = "Death"),
    @JsonSubTypes.Type(value = DeviceExposure.class, name = "DeviceExposure"),
    @JsonSubTypes.Type(value = DoseEra.class, name = "DoseEra"),
    @JsonSubTypes.Type(value = DrugEra.class, name = "DrugEra"),
    @JsonSubTypes.Type(value = DrugExposure.class, name = "DrugExposure"),
    @JsonSubTypes.Type(value = LocationRegion.class, name = "LocationRegion"),
    @JsonSubTypes.Type(value = Measurement.class, name = "Measurement"),
    @JsonSubTypes.Type(value = Observation.class, name = "Observation"),
    @JsonSubTypes.Type(value = ObservationPeriod.class, name = "ObservationPeriod"),
    @JsonSubTypes.Type(value = PayerPlanPeriod.class, name = "PayerPlanPeriod"),
    @JsonSubTypes.Type(value = ProcedureOccurrence.class, name = "ProcedureOccurrence"),
    @JsonSubTypes.Type(value = Specimen.class, name = "Specimen"),
    @JsonSubTypes.Type(value = VisitOccurrence.class, name = "VisitOccurrence"),
    @JsonSubTypes.Type(value = VisitDetail.class, name = "VisitDetail")
})
public abstract class Criteria {
    @JsonProperty("CorrelatedCriteria")
    public CriteriaGroup correlatedCriteria;
    @JsonProperty("DateAdjustment")
    public DateAdjustment dateAdjustment;
}
