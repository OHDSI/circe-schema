package org.ohdsi.circe.cohortdefinition;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DateOffsetStrategy extends EndStrategy {

    public enum DateField {
        StartDate,
        EndDate
    }

    @JsonProperty("DateField")
    public DateField dateField = DateField.StartDate;
    @JsonProperty("Offset")
    public int offset = 0;
}
