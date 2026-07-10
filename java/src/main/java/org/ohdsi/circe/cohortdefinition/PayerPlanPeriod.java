package org.ohdsi.circe.cohortdefinition;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.ohdsi.circe.vocabulary.Concept;

@JsonTypeName("PayerPlanPeriod")
public class PayerPlanPeriod extends Criteria {
    @JsonProperty("First") public Boolean first;
    @JsonProperty("PeriodStartDate") public DateRange periodStartDate;
    @JsonProperty("PeriodEndDate") public DateRange periodEndDate;
    @JsonProperty("UserDefinedPeriod") public Period userDefinedPeriod;
    @JsonProperty("PeriodLength") public NumericRange periodLength;
    @JsonProperty("AgeAtStart") public NumericRange ageAtStart;
    @JsonProperty("AgeAtEnd") public NumericRange ageAtEnd;
    @JsonProperty("Gender") public Concept[] gender;
    @JsonProperty("GenderCS") public ConceptSetSelection genderCS;
    @JsonProperty("PayerConcept") public Integer payerConcept;
    @JsonProperty("PlanConcept") public Integer planConcept;
    @JsonProperty("SponsorConcept") public Integer sponsorConcept;
    @JsonProperty("StopReasonConcept") public Integer stopReasonConcept;
    @JsonProperty("PayerSourceConcept") public Integer payerSourceConcept;
    @JsonProperty("PlanSourceConcept") public Integer planSourceConcept;
    @JsonProperty("SponsorSourceConcept") public Integer sponsorSourceConcept;
    @JsonProperty("StopReasonSourceConcept") public Integer stopReasonSourceConcept;
}
