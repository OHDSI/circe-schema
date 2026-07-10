package org.ohdsi.circe.cohortdefinition;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
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

    private static final ObjectMapper OBJECT_MAPPER = createObjectMapper();

    private static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper;
    }

    public static ObjectMapper getObjectMapper() {
        return OBJECT_MAPPER;
    }

    public static void registerCriteriaSubtype(Class<? extends Criteria> clazz) {
        OBJECT_MAPPER.registerSubtypes(clazz);
        com.fasterxml.jackson.annotation.JsonTypeName annotation =
                clazz.getAnnotation(com.fasterxml.jackson.annotation.JsonTypeName.class);
        if (annotation != null) {
            CriteriaRegistry.add(annotation.value(), clazz);
        }
    }

    public static CohortExpression fromJson(String json) {
        try {
            return OBJECT_MAPPER.readValue(json, CohortExpression.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse CohortExpression JSON", e);
        }
    }

    public String toJson() {
        try {
            return OBJECT_MAPPER.writeValueAsString(this);
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize CohortExpression", e);
        }
    }
}
