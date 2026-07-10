package org.ohdsi.circe.cohortdefinition;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.ohdsi.circe.vocabulary.Concept;

@JsonTypeName("Specimen")
public class Specimen extends Criteria {
    @JsonProperty("CodesetId") public Integer codesetId;
    @JsonProperty("First") public Boolean first;
    @JsonProperty("OccurrenceStartDate") public DateRange occurrenceStartDate;
    @JsonProperty("OccurrenceEndDate") public DateRange occurrenceEndDate;
    @JsonProperty("SpecimenType") public Concept[] specimenType;
    @JsonProperty("SpecimenTypeCS") public ConceptSetSelection specimenTypeCS;
    @JsonProperty("SpecimenTypeExclude") public Boolean specimenTypeExclude;
    @JsonProperty("SpecimenSourceConcept") public Integer specimenSourceConcept;
    @JsonProperty("Quantity") public NumericRange quantity;
    @JsonProperty("Unit") public Concept[] unit;
    @JsonProperty("UnitCS") public ConceptSetSelection unitCS;
    @JsonProperty("AnatomicSite") public Concept[] anatomicSite;
    @JsonProperty("AnatomicSiteCS") public ConceptSetSelection anatomicSiteCS;
    @JsonProperty("DiseaseStatus") public Concept[] diseaseStatus;
    @JsonProperty("DiseaseStatusCS") public ConceptSetSelection diseaseStatusCS;
    @JsonProperty("SourceId") public TextFilter sourceId;
    @JsonProperty("Age") public NumericRange age;
    @JsonProperty("Gender") public Concept[] gender;
    @JsonProperty("GenderCS") public ConceptSetSelection genderCS;
}
