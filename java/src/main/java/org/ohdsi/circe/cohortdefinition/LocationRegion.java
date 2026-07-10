package org.ohdsi.circe.cohortdefinition;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("LocationRegion")
public class LocationRegion extends Criteria {
    @JsonProperty("CodesetId") public Integer codesetId;
}
