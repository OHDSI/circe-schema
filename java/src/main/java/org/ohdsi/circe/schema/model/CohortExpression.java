package org.ohdsi.circe.schema.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CohortExpression {

    @JsonProperty("Title")
    public String title;

    @JsonProperty("PrimaryCriteria")
    public PrimaryCriteria primaryCriteria;

    @JsonProperty("AdditionalCriteria")
    public Groups.CriteriaGroup additionalCriteria;

    @JsonProperty("ConceptSets")
    public List<Vocabulary.ConceptSet> conceptSets = new ArrayList<>();

    @JsonProperty("QualifiedLimit")
    public CoreTypes.ResultLimit qualifiedLimit;

    @JsonProperty("ExpressionLimit")
    public CoreTypes.ResultLimit expressionLimit;

    @JsonProperty("InclusionRules")
    public List<Groups.InclusionRule> inclusionRules = new ArrayList<>();

    @JsonProperty("EndStrategy")
    public BaseEndStrategy endStrategy;

    @JsonProperty("CensoringCriteria")
    public List<Criteria> censoringCriteria = new ArrayList<>();

    @JsonProperty("CollapseSettings")
    public CoreTypes.CollapseSettings collapseSettings;

    @JsonProperty("CensorWindow")
    public CoreTypes.Period censorWindow;

    @JsonProperty("cdmVersionRange")
    public String cdmVersionRange;

    public static CohortExpression fromJson(String json) {
        try {
            ObjectMapper mapper = createObjectMapper();
            return mapper.readValue(json, CohortExpression.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse CohortExpression JSON", e);
        }
    }

    public String toJson() {
        try {
            ObjectMapper mapper = createObjectMapper();
            return mapper.writeValueAsString(this);
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize CohortExpression", e);
        }
    }

    public static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        return mapper;
    }
}
