package org.ohdsi.circe.cohortdefinition;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Period {
    @JsonProperty("StartDate")
    public String startDate;
    @JsonProperty("EndDate")
    public String endDate;
}
