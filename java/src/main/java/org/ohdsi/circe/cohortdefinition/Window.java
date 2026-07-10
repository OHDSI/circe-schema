package org.ohdsi.circe.cohortdefinition;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Window {

    public static class Endpoint {
        @JsonProperty("Days")
        public Integer days;
        @JsonProperty("Coeff")
        public int coeff;
    }

    @JsonProperty("Start")
    public Endpoint start;
    @JsonProperty("End")
    public Endpoint end;
    @JsonProperty("UseIndexEnd")
    public Boolean useIndexEnd;
    @JsonProperty("UseEventEnd")
    public Boolean useEventEnd;
}
