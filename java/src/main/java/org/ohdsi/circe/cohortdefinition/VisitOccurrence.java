package org.ohdsi.circe.cohortdefinition;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.ohdsi.circe.vocabulary.Concept;

@JsonTypeName("VisitOccurrence")
public class VisitOccurrence extends Criteria {
    @JsonProperty("CodesetId") public Integer codesetId;
    @JsonProperty("First") public Boolean first;
    @JsonProperty("OccurrenceStartDate") public DateRange occurrenceStartDate;
    @JsonProperty("OccurrenceEndDate") public DateRange occurrenceEndDate;
    @JsonProperty("VisitType") public Concept[] visitType;
    @JsonProperty("VisitTypeCS") public ConceptSetSelection visitTypeCS;
    @JsonProperty("VisitTypeExclude") public Boolean visitTypeExclude;
    @JsonProperty("VisitSourceConcept") public Integer visitSourceConcept;
    @JsonProperty("VisitLength") public NumericRange visitLength;
    @JsonProperty("Age") public NumericRange age;
    @JsonProperty("Gender") public Concept[] gender;
    @JsonProperty("GenderCS") public ConceptSetSelection genderCS;
    @JsonProperty("ProviderSpecialty") public Concept[] providerSpecialty;
    @JsonProperty("ProviderSpecialtyCS") public ConceptSetSelection providerSpecialtyCS;
    @JsonProperty("PlaceOfService") public Concept[] placeOfService;
    @JsonProperty("PlaceOfServiceCS") public ConceptSetSelection placeOfServiceCS;
    @JsonProperty("PlaceOfServiceLocation") public Integer placeOfServiceLocation;
}
