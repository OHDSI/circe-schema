package org.ohdsi.circe.schema.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EndStrategy {
}

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
@JsonSubTypes({
    @Type(value = DateOffsetStrategy.class, name = "DateOffset"),
    @Type(value = CustomEraStrategy.class, name = "CustomEra")
})
abstract class BaseEndStrategy {
}

@JsonTypeName("DateOffset")
class DateOffsetStrategy extends BaseEndStrategy {
    @JsonProperty("Offset") public int offset;
    @JsonProperty("DateField") public String dateField;
}

@JsonTypeName("CustomEra")
class CustomEraStrategy extends BaseEndStrategy {
    @JsonProperty("DrugCodesetId") public Integer drugCodesetId;
    @JsonProperty("GapDays") public int gapDays;
    @JsonProperty("Offset") public int offset;
    @JsonProperty("DaysSupplyOverride") public Integer daysSupplyOverride;
}
