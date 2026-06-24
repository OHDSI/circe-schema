package org.ohdsi.circe.schema.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Groups {

    public static class Occurrence {
        @JsonProperty("Type") public int type;
        @JsonProperty("Count") public int count;
        @JsonProperty("IsDistinct") public boolean isDistinct;
        @JsonProperty("CountColumn") public String countColumn;
    }

    public static class WindowedCriteria {
        @JsonProperty("Criteria") public Criteria criteria;
        @JsonProperty("StartWindow") public CoreTypes.Window startWindow;
        @JsonProperty("EndWindow") public CoreTypes.Window endWindow;
        @JsonProperty("RestrictVisit") public boolean restrictVisit;
        @JsonProperty("IgnoreObservationPeriod") public boolean ignoreObservationPeriod;
    }

    public static class CorelatedCriteria extends WindowedCriteria {
        @JsonProperty("Occurrence") public Occurrence occurrence;
    }

    public static class DemographicCriteria {
        @JsonProperty("Age") public CoreTypes.NumericRange age;
        @JsonProperty("Gender") public List<Vocabulary.Concept> gender;
        @JsonProperty("GenderCS") public CoreTypes.ConceptSetSelection genderCS;
        @JsonProperty("Race") public List<Vocabulary.Concept> race;
        @JsonProperty("RaceCS") public CoreTypes.ConceptSetSelection raceCS;
        @JsonProperty("Ethnicity") public List<Vocabulary.Concept> ethnicity;
        @JsonProperty("EthnicityCS") public CoreTypes.ConceptSetSelection ethnicityCS;
        @JsonProperty("OccurrenceStartDate") public CoreTypes.DateRange occurrenceStartDate;
        @JsonProperty("OccurrenceEndDate") public CoreTypes.DateRange occurrenceEndDate;
    }

    public static class CriteriaGroup {
        @JsonProperty("Type") public String type;
        @JsonProperty("Count") public Integer count;
        @JsonProperty("CriteriaList") public List<CorelatedCriteria> criteriaList = new ArrayList<>();
        @JsonProperty("Groups") public List<CriteriaGroup> groups = new ArrayList<>();
        @JsonProperty("DemographicCriteriaList") public List<DemographicCriteria> demographicCriteriaList = new ArrayList<>();
    }

    public static class InclusionRule {
        @JsonProperty("name") public String name;
        @JsonProperty("description") public String description;
        @JsonProperty("expression") public CriteriaGroup expression;
    }
}
