package org.ohdsi.circe.cohortdefinition;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WindowedCriteria {
    @JsonProperty("Criteria")
    public Criteria criteria;
    @JsonProperty("StartWindow")
    public Window startWindow;
    @JsonProperty("EndWindow")
    public Window endWindow;
    @JsonProperty("RestrictVisit")
    public boolean restrictVisit = false;
    @JsonProperty("IgnoreObservationPeriod")
    public boolean ignoreObservationPeriod = false;
}
