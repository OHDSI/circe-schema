package org.ohdsi.circe.cohortdefinition;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DateAdjustment {

    public enum DateType {
        @JsonProperty("START_DATE") START_DATE,
        @JsonProperty("END_DATE") END_DATE
    };

    @JsonProperty("StartWith")
    public DateType startWith = DateType.START_DATE;
    @JsonProperty("StartOffset")
    public int startOffset = 0;
    @JsonProperty("EndWith")
    public DateType endWith = DateType.END_DATE;
    @JsonProperty("EndOffset")
    public int endOffset = 0;
}
