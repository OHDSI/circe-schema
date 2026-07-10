package org.ohdsi.circe.cohortdefinition;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NumericRange {
    @JsonProperty("Value")
    public Number value;
    @JsonProperty("Extent")
    public Number extent;
    @JsonProperty("Op")
    public String op;
}
