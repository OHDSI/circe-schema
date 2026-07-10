package org.ohdsi.circe.cohortdefinition;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConceptSetSelection {
    @JsonProperty("CodesetId")
    public Integer codesetId;
    @JsonProperty("IsExclusion")
    public boolean isExclusion = false;
}
