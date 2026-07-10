package org.ohdsi.circe.cohortdefinition;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PrimaryCriteria {
    @JsonProperty("CriteriaList")
    public Criteria[] criteriaList = new Criteria[0];
    @JsonProperty("ObservationWindow")
    public ObservationFilter observationWindow;
    @JsonProperty("PrimaryCriteriaLimit")
    public ResultLimit primaryLimit = new ResultLimit();
}
