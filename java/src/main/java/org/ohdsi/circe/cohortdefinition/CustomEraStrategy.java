package org.ohdsi.circe.cohortdefinition;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomEraStrategy extends EndStrategy {
    @JsonProperty("DrugCodesetId")
    public Integer drugCodesetId;
    @JsonProperty("GapDays")
    public int gapDays = 0;
    @JsonProperty("Offset")
    public int offset = 0;
    @JsonProperty("DaysSupplyOverride")
    public Integer daysSupplyOverride = null;
}
