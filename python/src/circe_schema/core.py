from enum import Enum
from typing import Any, Optional, Union

from pydantic import AliasChoices, Field

from .base import CirceBaseModel


class CollapseType(str, Enum):
    ERA = "ERA"
    COLLAPSE = "collapse"
    NO_COLLAPSE = "no_collapse"


class DateType(str, Enum):
    START_DATE = "start_date"
    END_DATE = "end_date"


class ResultLimit(CirceBaseModel):
    type: Optional[str] = Field(
        default=None,
        validation_alias=AliasChoices("Type", "type"),
        serialization_alias="Type",
    )


class Period(CirceBaseModel):
    start_date: Optional[str] = Field(
        default=None,
        validation_alias=AliasChoices("StartDate", "startDate"),
        serialization_alias="StartDate",
    )
    end_date: Optional[str] = Field(
        default=None,
        validation_alias=AliasChoices("EndDate", "endDate"),
        serialization_alias="EndDate",
    )


class DateRange(CirceBaseModel):
    op: Optional[str] = Field(
        default=None,
        validation_alias=AliasChoices("Op", "op"),
        serialization_alias="Op",
    )
    value: Optional[Union[str, float]] = Field(
        default=None,
        validation_alias=AliasChoices("Value", "value"),
        serialization_alias="Value",
    )
    extent: Optional[Union[str, float]] = Field(
        default=None,
        validation_alias=AliasChoices("Extent", "extent"),
        serialization_alias="Extent",
    )


class NumericRange(CirceBaseModel):
    op: Optional[str] = Field(
        default=None,
        validation_alias=AliasChoices("Op", "op"),
        serialization_alias="Op",
    )
    value: Optional[Union[int, float]] = Field(
        default=None,
        validation_alias=AliasChoices("Value", "value"),
        serialization_alias="Value",
    )
    extent: Optional[Union[int, float]] = Field(
        default=None,
        validation_alias=AliasChoices("Extent", "extent"),
        serialization_alias="Extent",
    )


class DateAdjustment(CirceBaseModel):
    start_offset: int = Field(
        validation_alias=AliasChoices("startOffset", "StartOffset"),
        serialization_alias="startOffset",
    )
    end_offset: int = Field(
        validation_alias=AliasChoices("endOffset", "EndOffset"),
        serialization_alias="endOffset",
    )
    start_with: Optional[DateType] = Field(
        default=DateType.START_DATE,
        validation_alias=AliasChoices("startWith", "StartWith"),
        serialization_alias="startWith",
    )
    end_with: Optional[DateType] = Field(
        default=DateType.END_DATE,
        validation_alias=AliasChoices("endWith", "EndWith"),
        serialization_alias="endWith",
    )


class ObservationFilter(CirceBaseModel):
    prior_days: int = Field(
        validation_alias=AliasChoices("PriorDays", "priorDays"),
        serialization_alias="PriorDays",
    )
    post_days: int = Field(
        validation_alias=AliasChoices("PostDays", "postDays"),
        serialization_alias="PostDays",
    )


class CollapseSettings(CirceBaseModel):
    era_pad: int = Field(
        validation_alias=AliasChoices("EraPad", "eraPad"),
        serialization_alias="EraPad",
    )
    collapse_type: Optional[CollapseType] = Field(
        default=CollapseType.ERA,
        validation_alias=AliasChoices("CollapseType", "collapseType"),
        serialization_alias="CollapseType",
    )


class ConceptSetSelection(CirceBaseModel):
    codeset_id: Optional[int] = Field(
        default=None,
        validation_alias=AliasChoices("CodesetId", "codesetId"),
        serialization_alias="CodesetId",
    )
    is_exclusion: bool = Field(
        default=False,
        validation_alias=AliasChoices("IsExclusion", "isExclusion"),
        serialization_alias="IsExclusion",
    )


class TextFilter(CirceBaseModel):
    text: Optional[str] = Field(
        default=None,
        validation_alias=AliasChoices("Text", "text"),
        serialization_alias="Text",
    )
    op: Optional[str] = Field(
        default=None,
        validation_alias=AliasChoices("Op", "op"),
        serialization_alias="Op",
    )


class WindowBound(CirceBaseModel):
    coeff: int = Field(
        validation_alias=AliasChoices("Coeff", "coeff"),
        serialization_alias="Coeff",
    )
    days: Optional[int] = Field(
        default=None,
        validation_alias=AliasChoices("Days", "days"),
        serialization_alias="Days",
    )


class Window(CirceBaseModel):
    start: Optional[WindowBound] = Field(
        default=None,
        validation_alias=AliasChoices("Start", "start"),
        serialization_alias="Start",
    )
    end: Optional[WindowBound] = Field(
        default=None,
        validation_alias=AliasChoices("End", "end"),
        serialization_alias="End",
    )
    use_event_end: Optional[bool] = Field(
        default=None,
        validation_alias=AliasChoices("UseEventEnd", "useEventEnd"),
        serialization_alias="UseEventEnd",
    )
    use_index_end: Optional[bool] = Field(
        default=None,
        validation_alias=AliasChoices("UseIndexEnd", "useIndexEnd"),
        serialization_alias="UseIndexEnd",
    )
