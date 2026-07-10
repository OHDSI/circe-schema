package org.ohdsi.circe.cohortdefinition;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CriteriaGroup {
    @JsonProperty("Type")
    public String type;
    @JsonProperty("Count")
    public Integer count;
    @JsonProperty("CriteriaList")
    public CorelatedCriteria[] criteriaList = new CorelatedCriteria[0];
    @JsonProperty("DemographicCriteriaList")
    public DemographicCriteria[] demographicCriteriaList = new DemographicCriteria[0];
    @JsonProperty("Groups")
    public CriteriaGroup[] groups = new CriteriaGroup[0];

    @JsonIgnore
    public boolean isEmpty() {
        return !(criteriaList.length > 0 || demographicCriteriaList.length > 0 || groups.length > 0);
    }
}
