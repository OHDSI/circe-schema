package org.ohdsi.circe.cohortdefinition;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResultLimit {
    @JsonProperty("Type")
    public String type = "First";
}
