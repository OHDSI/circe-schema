package org.ohdsi.circe.cohortdefinition;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.ohdsi.circe.vocabulary.Concept;

@JsonTypeName("DeviceExposure")
public class DeviceExposure extends Criteria {
    @JsonProperty("CodesetId") public Integer codesetId;
    @JsonProperty("First") public Boolean first;
    @JsonProperty("OccurrenceStartDate") public DateRange occurrenceStartDate;
    @JsonProperty("OccurrenceEndDate") public DateRange occurrenceEndDate;
    @JsonProperty("DeviceType") public Concept[] deviceType;
    @JsonProperty("DeviceTypeCS") public ConceptSetSelection deviceTypeCS;
    @JsonProperty("DeviceTypeExclude") public Boolean deviceTypeExclude;
    @JsonProperty("DeviceSourceConcept") public Integer deviceSourceConcept;
    @JsonProperty("UniqueDeviceId") public TextFilter uniqueDeviceId;
    @JsonProperty("Quantity") public NumericRange quantity;
    @JsonProperty("Age") public NumericRange age;
    @JsonProperty("Gender") public Concept[] gender;
    @JsonProperty("GenderCS") public ConceptSetSelection genderCS;
    @JsonProperty("ProviderSpecialty") public Concept[] providerSpecialty;
    @JsonProperty("ProviderSpecialtyCS") public ConceptSetSelection providerSpecialtyCS;
    @JsonProperty("VisitType") public Concept[] visitType;
    @JsonProperty("VisitTypeCS") public ConceptSetSelection visitTypeCS;
}
