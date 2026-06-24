package org.ohdsi.circe.schema.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Vocabulary {

    public static class Concept {
        @JsonProperty("CONCEPT_ID") public Integer conceptId;
        @JsonProperty("CONCEPT_NAME") public String conceptName;
        @JsonProperty("CONCEPT_CODE") public String conceptCode;
        @JsonProperty("CONCEPT_CLASS_ID") public String conceptClassId;
        @JsonProperty("STANDARD_CONCEPT") public String standardConcept;
        @JsonProperty("INVALID_REASON") public String invalidReason;
        @JsonProperty("DOMAIN_ID") public String domainId;
        @JsonProperty("VOCABULARY_ID") public String vocabularyId;
    }

    public static class ConceptSetItem {
        @JsonProperty("concept") public Concept concept;
        @JsonProperty("isExcluded") public boolean isExcluded;
        @JsonProperty("includeMapped") public boolean includeMapped;
        @JsonProperty("includeDescendants") public boolean includeDescendants;
    }

    public static class ConceptSetExpression {
        @JsonProperty("items") public List<ConceptSetItem> items;
        @JsonProperty("concept") public Concept concept;
        @JsonProperty("isExcluded") public Boolean isExcluded;
        @JsonProperty("includeMapped") public Boolean includeMapped;
        @JsonProperty("includeDescendants") public Boolean includeDescendants;
    }

    public static class ConceptSet {
        @JsonProperty("id") public int id;
        @JsonProperty("name") public String name;
        @JsonProperty("expression") public ConceptSetExpression expression;
    }
}
