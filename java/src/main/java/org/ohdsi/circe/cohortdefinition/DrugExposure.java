package org.ohdsi.circe.cohortdefinition;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.ohdsi.circe.vocabulary.Concept;

@JsonTypeName("DrugExposure")
public class DrugExposure extends Criteria {
    @JsonProperty("CodesetId") public Integer codesetId;
    @JsonProperty("First") public Boolean first;
    @JsonProperty("OccurrenceStartDate") public DateRange occurrenceStartDate;
    @JsonProperty("OccurrenceEndDate") public DateRange occurrenceEndDate;
    @JsonProperty("DrugType") public Concept[] drugType;
    @JsonProperty("DrugTypeCS") public ConceptSetSelection drugTypeCS;
    @JsonProperty("DrugTypeExclude") public Boolean drugTypeExclude;
    @JsonProperty("DrugSourceConcept") public Integer drugSourceConcept;
    @JsonProperty("StopReason") public TextFilter stopReason;
    @JsonProperty("Age") public NumericRange age;
    @JsonProperty("Gender") public Concept[] gender;
    @JsonProperty("GenderCS") public ConceptSetSelection genderCS;
    @JsonProperty("ProviderSpecialty") public Concept[] providerSpecialty;
    @JsonProperty("ProviderSpecialtyCS") public ConceptSetSelection providerSpecialtyCS;
    @JsonProperty("VisitType") public Concept[] visitType;
    @JsonProperty("VisitTypeCS") public ConceptSetSelection visitTypeCS;
    @JsonProperty("RouteConcept") public Concept[] routeConcept;
    @JsonProperty("RouteConceptCS") public ConceptSetSelection routeConceptCS;
    @JsonProperty("Quantity") public NumericRange quantity;
    @JsonProperty("DaysSupply") public NumericRange daysSupply;
    @JsonProperty("Refills") public NumericRange refills;
    @JsonProperty("EffectiveDrugDose") public NumericRange effectiveDrugDose;
    @JsonProperty("DoseUnit") public Concept[] doseUnit;
    @JsonProperty("DoseUnitCS") public ConceptSetSelection doseUnitCS;
    @JsonProperty("LotNumber") public TextFilter lotNumber;
}
