package org.ohdsi.circe.cohortdefinition;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
@JsonSubTypes({
    @JsonSubTypes.Type(value = DateOffsetStrategy.class, name = "DateOffset"),
    @JsonSubTypes.Type(value = CustomEraStrategy.class, name = "CustomEra")
})
public abstract class EndStrategy {
}
