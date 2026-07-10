package org.ohdsi.circe.cohortdefinition;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TextFilter {
    @JsonProperty("Text")
    public String text;
    @JsonProperty("Op")
    public String op;
}
