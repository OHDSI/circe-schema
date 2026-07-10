package org.ohdsi.circe.cohortdefinition;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ObservationFilter {
    @JsonProperty("PriorDays")
    public int priorDays;
    @JsonProperty("PostDays")
    public int postDays;
}
