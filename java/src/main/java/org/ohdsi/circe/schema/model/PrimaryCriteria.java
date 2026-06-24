package org.ohdsi.circe.schema.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PrimaryCriteria {
    @JsonProperty("CriteriaList")
    public List<Criteria> criteriaList;

    @JsonProperty("ObservationWindow")
    public CoreTypes.ObservationFilter observationWindow;

    @JsonProperty("PrimaryLimit")
    public CoreTypes.ResultLimit primaryLimit;
}
