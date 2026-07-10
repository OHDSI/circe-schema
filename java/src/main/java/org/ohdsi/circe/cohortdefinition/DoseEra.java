package org.ohdsi.circe.cohortdefinition;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.ohdsi.circe.vocabulary.Concept;

@JsonTypeName("DoseEra")
public class DoseEra extends Criteria {
    @JsonProperty("CodesetId") public Integer codesetId;
    @JsonProperty("First") public Boolean first;
    @JsonProperty("EraStartDate") public DateRange eraStartDate;
    @JsonProperty("EraEndDate") public DateRange eraEndDate;
    @JsonProperty("Unit") public Concept[] unit;
    @JsonProperty("UnitCS") public ConceptSetSelection unitCS;
    @JsonProperty("DoseValue") public NumericRange doseValue;
    @JsonProperty("EraLength") public NumericRange eraLength;
    @JsonProperty("AgeAtStart") public NumericRange ageAtStart;
    @JsonProperty("AgeAtEnd") public NumericRange ageAtEnd;
    @JsonProperty("Gender") public Concept[] gender;
    @JsonProperty("GenderCS") public ConceptSetSelection genderCS;
}
