package org.ohdsi.circe.cohortdefinition;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CollapseSettings {
    @JsonProperty("CollapseType")
    public CollapseType collapseType = CollapseType.ERA;
    @JsonProperty("EraPad")
    public int eraPad = 0;
}
