package org.ohdsi.circe.vocabulary;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.Objects;

@JsonInclude(Include.ALWAYS)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
    "CONCEPT_ID", "CONCEPT_NAME", "STANDARD_CONCEPT",
    "STANDARD_CONCEPT_CAPTION", "INVALID_REASON", "INVALID_REASON_CAPTION",
    "CONCEPT_CODE", "DOMAIN_ID", "VOCABULARY_ID", "CONCEPT_CLASS_ID"
})
public class Concept {
    @JsonProperty("CONCEPT_ID")
    public Long conceptId;
    @JsonProperty("CONCEPT_NAME")
    public String conceptName;
    @JsonProperty("STANDARD_CONCEPT")
    public String standardConcept;
    @JsonProperty("INVALID_REASON")
    public String invalidReason;
    @JsonProperty("CONCEPT_CODE")
    public String conceptCode;
    @JsonProperty("DOMAIN_ID")
    public String domainId;
    @JsonProperty("VOCABULARY_ID")
    public String vocabularyId;
    @JsonProperty("CONCEPT_CLASS_ID")
    public String conceptClassId;

    @JsonProperty("STANDARD_CONCEPT_CAPTION")
    public String getStandardConceptCaption() {
        if (standardConcept == null) return "Unknown";
        switch (standardConcept) {
            case "N": return "Non-Standard";
            case "S": return "Standard";
            case "C": return "Classification";
            default:  return "Unknown";
        }
    }

    @JsonProperty("INVALID_REASON_CAPTION")
    public String getInvalidReasonCaption() {
        if (invalidReason == null) return "Unknown";
        switch (invalidReason) {
            case "V": return "Valid";
            case "D": return "Invalid";
            case "U": return "Invalid";
            default:  return "Unknown";
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Concept)) return false;
        Concept other = (Concept) o;
        return Objects.equals(conceptId, other.conceptId)
            && Objects.equals(conceptName, other.conceptName)
            && Objects.equals(standardConcept, other.standardConcept)
            && Objects.equals(invalidReason, other.invalidReason)
            && Objects.equals(conceptCode, other.conceptCode)
            && Objects.equals(domainId, other.domainId)
            && Objects.equals(vocabularyId, other.vocabularyId)
            && Objects.equals(conceptClassId, other.conceptClassId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(conceptId, conceptName, standardConcept, invalidReason, conceptCode, domainId, vocabularyId, conceptClassId);
    }
}
