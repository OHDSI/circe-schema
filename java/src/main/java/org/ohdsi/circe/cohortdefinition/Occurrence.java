package org.ohdsi.circe.cohortdefinition;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Occurrence {
    public static final int EXACTLY = 0;
    public static final int AT_MOST = 1;
    public static final int AT_LEAST = 2;

    @JsonProperty("Type")
    public int type;
    @JsonProperty("Count")
    public int count;
    @JsonProperty("IsDistinct")
    public boolean isDistinct;
    @JsonProperty("CountColumn")
    public String countColumn;
}
