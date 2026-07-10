package org.ohdsi.circe.cohortdefinition;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.ohdsi.circe.vocabulary.Concept;

@JsonTypeName("ProcedureOccurrence")
public class ProcedureOccurrence extends Criteria {
    @JsonProperty("CodesetId") public Integer codesetId;
    @JsonProperty("First") public Boolean first;
    @JsonProperty("OccurrenceStartDate") public DateRange occurrenceStartDate;
    @JsonProperty("OccurrenceEndDate") public DateRange occurrenceEndDate;
    @JsonProperty("ProcedureType") public Concept[] procedureType;
    @JsonProperty("ProcedureTypeCS") public ConceptSetSelection procedureTypeCS;
    @JsonProperty("ProcedureTypeExclude") public Boolean procedureTypeExclude;
    @JsonProperty("ProcedureSourceConcept") public Integer procedureSourceConcept;
    @JsonProperty("Quantity") public NumericRange quantity;
    @JsonProperty("Age") public NumericRange age;
    @JsonProperty("Gender") public Concept[] gender;
    @JsonProperty("GenderCS") public ConceptSetSelection genderCS;
    @JsonProperty("ProviderSpecialty") public Concept[] providerSpecialty;
    @JsonProperty("ProviderSpecialtyCS") public ConceptSetSelection providerSpecialtyCS;
    @JsonProperty("VisitType") public Concept[] visitType;
    @JsonProperty("VisitTypeCS") public ConceptSetSelection visitTypeCS;
    @JsonProperty("Modifier") public Concept[] modifier;
    @JsonProperty("ModifierCS") public ConceptSetSelection modifierCS;
}
