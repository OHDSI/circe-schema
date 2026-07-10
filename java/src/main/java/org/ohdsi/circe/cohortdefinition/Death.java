package org.ohdsi.circe.cohortdefinition;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.ohdsi.circe.vocabulary.Concept;

@JsonTypeName("Death")
public class Death extends Criteria {
    @JsonProperty("CodesetId") public Integer codesetId;
    @JsonProperty("OccurrenceStartDate") public DateRange occurrenceStartDate;
    @JsonProperty("OccurrenceEndDate") public DateRange occurrenceEndDate;
    @JsonProperty("DeathType") public Concept[] deathType;
    @JsonProperty("DeathTypeCS") public ConceptSetSelection deathTypeCS;
    @JsonProperty("DeathTypeExclude") public Boolean deathTypeExclude;
    @JsonProperty("DeathSourceConcept") public Integer deathSourceConcept;
    @JsonProperty("CauseSourceConcept") public Integer causeSourceConcept;
    @JsonProperty("CauseSourceConceptCS") public ConceptSetSelection causeSourceConceptCS;
    @JsonProperty("Age") public NumericRange age;
    @JsonProperty("Gender") public Concept[] gender;
    @JsonProperty("GenderCS") public ConceptSetSelection genderCS;
}
