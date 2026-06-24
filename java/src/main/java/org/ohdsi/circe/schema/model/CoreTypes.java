package org.ohdsi.circe.schema.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CoreTypes {

    public static class ResultLimit {
        @JsonProperty("Type")
        public String type;
    }

    public static class Period {
        @JsonProperty("StartDate")
        public String startDate;
        @JsonProperty("EndDate")
        public String endDate;
    }

    public static class DateRange {
        @JsonProperty("Op")
        public String op;
        @JsonProperty("Value")
        public Object value;
        @JsonProperty("Extent")
        public Object extent;
    }

    public static class NumericRange {
        @JsonProperty("Op")
        public String op;
        @JsonProperty("Value")
        public Object value;
        @JsonProperty("Extent")
        public Object extent;
    }

    public static class DateAdjustment {
        @JsonProperty("startOffset")
        public int startOffset;
        @JsonProperty("endOffset")
        public int endOffset;
        @JsonProperty("startWith")
        public String startWith;
        @JsonProperty("endWith")
        public String endWith;
    }

    public static class ObservationFilter {
        @JsonProperty("PriorDays")
        public int priorDays;
        @JsonProperty("PostDays")
        public int postDays;
    }

    public static class CollapseSettings {
        @JsonProperty("EraPad")
        public int eraPad;
        @JsonProperty("CollapseType")
        public String collapseType;
    }

    public static class WindowBound {
        @JsonProperty("Coeff")
        public int coeff;
        @JsonProperty("Days")
        public Integer days;
    }

    public static class Window {
        @JsonProperty("Start")
        public WindowBound start;
        @JsonProperty("End")
        public WindowBound end;
        @JsonProperty("UseEventEnd")
        public Boolean useEventEnd;
        @JsonProperty("UseIndexEnd")
        public Boolean useIndexEnd;
    }

    public static class TextFilter {
        @JsonProperty("Text")
        public String text;
        @JsonProperty("Op")
        public String op;
    }

    public static class ConceptSetSelection {
        @JsonProperty("CodesetId")
        public Integer codesetId;
        @JsonProperty("IsExclusion")
        public boolean isExclusion;
    }
}
