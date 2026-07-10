package org.ohdsi.circe.cohortdefinition;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.ohdsi.circe.vocabulary.Concept;

@JsonTypeName("Measurement")
public class Measurement extends Criteria {
    @JsonProperty("CodesetId") public Integer codesetId;
    @JsonProperty("First") public Boolean first;
    @JsonProperty("OccurrenceStartDate") public DateRange occurrenceStartDate;
    @JsonProperty("OccurrenceEndDate") public DateRange occurrenceEndDate;
    @JsonProperty("MeasurementType") public Concept[] measurementType;
    @JsonProperty("MeasurementTypeCS") public ConceptSetSelection measurementTypeCS;
    @JsonProperty("MeasurementTypeExclude") public Boolean measurementTypeExclude;
    @JsonProperty("MeasurementSourceConcept") public Integer measurementSourceConcept;
    @JsonProperty("ValueAsNumber") public NumericRange valueAsNumber;
    @JsonProperty("ValueAsString") public TextFilter valueAsString;
    @JsonProperty("ValueAsConcept") public Concept[] valueAsConcept;
    @JsonProperty("ValueAsConceptCS") public ConceptSetSelection valueAsConceptCS;
    @JsonProperty("Operator") public Concept[] operator;
    @JsonProperty("OperatorCS") public ConceptSetSelection operatorCS;
    @JsonProperty("Unit") public Concept[] unit;
    @JsonProperty("UnitCS") public ConceptSetSelection unitCS;
    @JsonProperty("RangeLow") public NumericRange rangeLow;
    @JsonProperty("RangeHigh") public NumericRange rangeHigh;
    @JsonProperty("RangeLowRatio") public NumericRange rangeLowRatio;
    @JsonProperty("RangeHighRatio") public NumericRange rangeHighRatio;
    @JsonProperty("Abnormal") public Boolean abnormal;
    @JsonProperty("Age") public NumericRange age;
    @JsonProperty("Gender") public Concept[] gender;
    @JsonProperty("GenderCS") public ConceptSetSelection genderCS;
    @JsonProperty("ProviderSpecialty") public Concept[] providerSpecialty;
    @JsonProperty("ProviderSpecialtyCS") public ConceptSetSelection providerSpecialtyCS;
    @JsonProperty("VisitType") public Concept[] visitType;
    @JsonProperty("VisitTypeCS") public ConceptSetSelection visitTypeCS;
}
