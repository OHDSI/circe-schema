from pydantic import BaseModel, ConfigDict


def to_pascal_case(name: str) -> str:
    parts = name.split("_")
    return "".join(p.capitalize() for p in parts if p)


class CirceBaseModel(BaseModel):
    """Base model for all Circe schema classes.

    Provides consistent JSON serialization with:
    - PascalCase aliases for Java compatibility
    - by_alias=True and exclude_none=True as defaults
    - allow extra fields for forward compatibility
    """

    def model_dump_json(self, **kwargs):
        kwargs.setdefault("by_alias", True)
        kwargs.setdefault("exclude_none", True)
        return super().model_dump_json(**kwargs)

    def model_dump(self, **kwargs):
        kwargs.setdefault("by_alias", True)
        kwargs.setdefault("exclude_none", True)
        return super().model_dump(**kwargs)

    model_config = ConfigDict(
        alias_generator=to_pascal_case,
        populate_by_name=True,
        extra="ignore",
    )
