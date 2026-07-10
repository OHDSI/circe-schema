package org.ohdsi.circe.cohortdefinition;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.ohdsi.analysis.Utils;
import org.ohdsi.circe.vocabulary.ConceptSet;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CohortExpression {

    @JsonProperty("Title")
    public String title;

    @JsonProperty("PrimaryCriteria")
    public PrimaryCriteria primaryCriteria;

    @JsonProperty("AdditionalCriteria")
    public CriteriaGroup additionalCriteria;

    @JsonProperty("ConceptSets")
    public ConceptSet[] conceptSets;

    @JsonProperty("QualifiedLimit")
    public ResultLimit qualifiedLimit = new ResultLimit();

    @JsonProperty("ExpressionLimit")
    public ResultLimit expressionLimit = new ResultLimit();

    @JsonProperty("InclusionRules")
    public List<InclusionRule> inclusionRules = new ArrayList<>();

    @JsonProperty("EndStrategy")
    public EndStrategy endStrategy;

    @JsonProperty("CensoringCriteria")
    public Criteria[] censoringCriteria;

    @JsonProperty("CollapseSettings")
    public CollapseSettings collapseSettings = new CollapseSettings();

    @JsonProperty("CensorWindow")
    public Period censorWindow;

    @JsonProperty("cdmVersionRange")
    private String cdmVersionRange;

    public String getCdmVersionRange() {
        return cdmVersionRange;
    }

    public void setCdmVersionRange(String cdmVersionRange) {
        this.cdmVersionRange = cdmVersionRange;
    }

    public static CohortExpression fromJson(String json) {
        return Utils.deserialize(json, new TypeReference<CohortExpression>() {});
    }

    public String toJson() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            return mapper.writeValueAsString(this);
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize CohortExpression", e);
        }
    }
}
