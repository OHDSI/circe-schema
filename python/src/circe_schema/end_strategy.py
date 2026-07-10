from typing import Any, Optional

from pydantic import AliasChoices, Field, model_serializer

from .base import CirceBaseModel


class EndStrategy(CirceBaseModel):
    """Base class for cohort end date strategies.

    Serialized polymorphically: ``{"DateOffset": {...}}`` or ``{"CustomEra": {...}}``.
    """
    @model_serializer(mode="wrap")
    def _serialize_polymorphic(self, serializer, info):
        data = serializer(self)
        if self.__class__.__name__ == "DateOffsetStrategy":
            return {"DateOffset": data}
        if self.__class__.__name__ == "CustomEraStrategy":
            return {"CustomEra": data}
        return data


class DateOffsetStrategy(EndStrategy):
    """Date offset end strategy. Defines cohort end as a fixed offset from a date field."""
    offset: int = Field(
        validation_alias=AliasChoices("Offset", "offset"),
        serialization_alias="Offset",
    )
    date_field: str = Field(
        validation_alias=AliasChoices("DateField", "dateField"),
        serialization_alias="DateField",
    )


class CustomEraStrategy(EndStrategy):
    """Custom era end strategy. Defines cohort end based on drug era persistence."""
    drug_codeset_id: Optional[int] = Field(
        default=None,
        validation_alias=AliasChoices("DrugCodesetId", "drugCodesetId"),
        serialization_alias="DrugCodesetId",
    )
    gap_days: int = Field(
        default=0,
        validation_alias=AliasChoices("GapDays", "gapDays"),
        serialization_alias="GapDays",
    )
    offset: int = Field(
        default=0,
        validation_alias=AliasChoices("Offset", "offset"),
        serialization_alias="Offset",
    )
    days_supply_override: Optional[int] = Field(
        default=None,
        validation_alias=AliasChoices("DaysSupplyOverride", "daysSupplyOverride"),
        serialization_alias="DaysSupplyOverride",
    )
