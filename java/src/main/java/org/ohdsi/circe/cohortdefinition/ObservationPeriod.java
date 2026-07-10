package org.ohdsi.circe.cohortdefinition;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.ohdsi.circe.vocabulary.Concept;

@JsonTypeName("ObservationPeriod")
public class ObservationPeriod extends Criteria {
    @JsonProperty("First") public Boolean first;
    @JsonProperty("PeriodStartDate") public DateRange periodStartDate;
    @JsonProperty("PeriodEndDate") public DateRange periodEndDate;
    @JsonProperty("PeriodType") public Concept[] periodType;
    @JsonProperty("PeriodTypeCS") public ConceptSetSelection periodTypeCS;
    @JsonProperty("UserDefinedPeriod") public Period userDefinedPeriod;
    @JsonProperty("PeriodLength") public NumericRange periodLength;
    @JsonProperty("AgeAtStart") public NumericRange ageAtStart;
    @JsonProperty("AgeAtEnd") public NumericRange ageAtEnd;
}
