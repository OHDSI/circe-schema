package org.ohdsi.circe.cohortdefinition;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DateRange {
    @JsonProperty("Value")
    public String value;
    @JsonProperty("Extent")
    public String extent;
    @JsonProperty("Op")
    public String op;
}
