package org.ohdsi.circe.cohortdefinition;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.ohdsi.circe.vocabulary.Concept;

@JsonTypeName("VisitDetail")
public class VisitDetail extends Criteria {
    @JsonProperty("CodesetId") public Integer codesetId;
    @JsonProperty("First") public Boolean first;
    @JsonProperty("VisitDetailStartDate") public DateRange visitDetailStartDate;
    @JsonProperty("VisitDetailEndDate") public DateRange visitDetailEndDate;
    @JsonProperty("VisitDetailType") public Concept[] visitDetailType;
    @JsonProperty("VisitDetailTypeCS") public ConceptSetSelection visitDetailTypeCS;
    @JsonProperty("VisitDetailTypeExclude") public Boolean visitDetailTypeExclude;
    @JsonProperty("VisitDetailSourceConcept") public Integer visitDetailSourceConcept;
    @JsonProperty("VisitDetailLength") public NumericRange visitDetailLength;
    @JsonProperty("Age") public NumericRange age;
    @JsonProperty("Gender") public Concept[] gender;
    @JsonProperty("GenderCS") public ConceptSetSelection genderCS;
    @JsonProperty("ProviderSpecialty") public Concept[] providerSpecialty;
    @JsonProperty("ProviderSpecialtyCS") public ConceptSetSelection providerSpecialtyCS;
    @JsonProperty("PlaceOfService") public Concept[] placeOfService;
    @JsonProperty("PlaceOfServiceCS") public ConceptSetSelection placeOfServiceCS;
    @JsonProperty("PlaceOfServiceLocation") public Integer placeOfServiceLocation;
    @JsonProperty("DischargeTo") public Concept[] dischargeTo;
    @JsonProperty("DischargeToCS") public ConceptSetSelection dischargeToCS;
}
