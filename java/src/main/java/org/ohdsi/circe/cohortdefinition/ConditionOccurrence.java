package org.ohdsi.circe.cohortdefinition;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.ohdsi.circe.vocabulary.Concept;

@JsonTypeName("ConditionOccurrence")
public class ConditionOccurrence extends Criteria {
    @JsonProperty("CodesetId") public Integer codesetId;
    @JsonProperty("First") public Boolean first;
    @JsonProperty("OccurrenceStartDate") public DateRange occurrenceStartDate;
    @JsonProperty("OccurrenceEndDate") public DateRange occurrenceEndDate;
    @JsonProperty("ConditionType") public Concept[] conditionType;
    @JsonProperty("ConditionTypeCS") public ConceptSetSelection conditionTypeCS;
    @JsonProperty("ConditionTypeExclude") public Boolean conditionTypeExclude;
    @JsonProperty("StopReason") public TextFilter stopReason;
    @JsonProperty("ConditionSourceConcept") public Integer conditionSourceConcept;
    @JsonProperty("Age") public NumericRange age;
    @JsonProperty("Gender") public Concept[] gender;
    @JsonProperty("GenderCS") public ConceptSetSelection genderCS;
    @JsonProperty("ProviderSpecialty") public Concept[] providerSpecialty;
    @JsonProperty("ProviderSpecialtyCS") public ConceptSetSelection providerSpecialtyCS;
    @JsonProperty("VisitType") public Concept[] visitType;
    @JsonProperty("VisitTypeCS") public ConceptSetSelection visitTypeCS;
    @JsonProperty("ConditionStatus") public Concept[] conditionStatus;
    @JsonProperty("ConditionStatusCS") public ConceptSetSelection conditionStatusCS;
}
