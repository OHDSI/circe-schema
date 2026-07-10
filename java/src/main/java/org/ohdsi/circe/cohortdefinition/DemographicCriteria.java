package org.ohdsi.circe.cohortdefinition;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.ohdsi.circe.vocabulary.Concept;

public class DemographicCriteria {
    @JsonProperty("Age")
    public NumericRange age;
    @JsonProperty("Gender")
    public Concept[] gender;
    @JsonProperty("GenderCS")
    public ConceptSetSelection genderCS;
    @JsonProperty("Race")
    public Concept[] race;
    @JsonProperty("RaceCS")
    public ConceptSetSelection raceCS;
    @JsonProperty("Ethnicity")
    public Concept[] ethnicity;
    @JsonProperty("EthnicityCS")
    public ConceptSetSelection ethnicityCS;
    @JsonProperty("OccurrenceStartDate")
    public DateRange occurrenceStartDate;
    @JsonProperty("OccurrenceEndDate")
    public DateRange occurrenceEndDate;
}
