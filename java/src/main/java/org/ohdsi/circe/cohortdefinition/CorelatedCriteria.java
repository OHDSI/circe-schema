package org.ohdsi.circe.cohortdefinition;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CorelatedCriteria extends WindowedCriteria {
    @JsonProperty("Occurrence")
    public Occurrence occurrence;
}
