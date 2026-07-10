package org.ohdsi.circe.cohortdefinition;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.ohdsi.circe.vocabulary.Concept;

@JsonTypeName("Observation")
public class Observation extends Criteria {
    @JsonProperty("CodesetId") public Integer codesetId;
    @JsonProperty("First") public Boolean first;
    @JsonProperty("OccurrenceStartDate") public DateRange occurrenceStartDate;
    @JsonProperty("OccurrenceEndDate") public DateRange occurrenceEndDate;
    @JsonProperty("ObservationType") public Concept[] observationType;
    @JsonProperty("ObservationTypeCS") public ConceptSetSelection observationTypeCS;
    @JsonProperty("ObservationTypeExclude") public Boolean observationTypeExclude;
    @JsonProperty("ObservationSourceConcept") public Integer observationSourceConcept;
    @JsonProperty("ValueAsNumber") public NumericRange valueAsNumber;
    @JsonProperty("ValueAsString") public TextFilter valueAsString;
    @JsonProperty("ValueAsConcept") public Concept[] valueAsConcept;
    @JsonProperty("ValueAsConceptCS") public ConceptSetSelection valueAsConceptCS;
    @JsonProperty("Unit") public Concept[] unit;
    @JsonProperty("UnitCS") public ConceptSetSelection unitCS;
    @JsonProperty("Qualifier") public Concept[] qualifier;
    @JsonProperty("QualifierCS") public ConceptSetSelection qualifierCS;
    @JsonProperty("Age") public NumericRange age;
    @JsonProperty("Gender") public Concept[] gender;
    @JsonProperty("GenderCS") public ConceptSetSelection genderCS;
    @JsonProperty("ProviderSpecialty") public Concept[] providerSpecialty;
    @JsonProperty("ProviderSpecialtyCS") public ConceptSetSelection providerSpecialtyCS;
    @JsonProperty("VisitType") public Concept[] visitType;
    @JsonProperty("VisitTypeCS") public ConceptSetSelection visitTypeCS;
}
