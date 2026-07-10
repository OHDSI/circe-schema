from typing import Any, List, Optional

from pydantic import AliasChoices, BaseModel, ConfigDict, Field


class Concept(BaseModel):
    """A concept from the OMOP standardized vocabulary."""
    concept_id: Optional[int] = Field(
        default=None,
        validation_alias=AliasChoices("ConceptId", "CONCEPT_ID", "conceptId"),
        serialization_alias="CONCEPT_ID",
    )
    concept_name: Optional[str] = Field(
        default=None,
        validation_alias=AliasChoices("ConceptName", "CONCEPT_NAME", "conceptName"),
        serialization_alias="CONCEPT_NAME",
    )
    concept_code: Optional[str] = Field(
        default=None,
        validation_alias=AliasChoices("ConceptCode", "CONCEPT_CODE", "conceptCode"),
        serialization_alias="CONCEPT_CODE",
    )
    concept_class_id: Optional[str] = Field(
        default=None,
        validation_alias=AliasChoices("ConceptClassId", "CONCEPT_CLASS_ID", "conceptClassId"),
        serialization_alias="CONCEPT_CLASS_ID",
    )
    standard_concept: Optional[str] = Field(
        default=None,
        validation_alias=AliasChoices("StandardConcept", "STANDARD_CONCEPT", "standardConcept"),
        serialization_alias="STANDARD_CONCEPT",
    )
    invalid_reason: Optional[str] = Field(
        default=None,
        validation_alias=AliasChoices("InvalidReason", "INVALID_REASON", "invalidReason"),
        serialization_alias="INVALID_REASON",
    )
    domain_id: Optional[str] = Field(
        default=None,
        validation_alias=AliasChoices("DomainId", "DOMAIN_ID", "domainId"),
        serialization_alias="DOMAIN_ID",
    )
    vocabulary_id: Optional[str] = Field(
        default=None,
        validation_alias=AliasChoices("VocabularyId", "VOCABULARY_ID", "vocabularyId"),
        serialization_alias="VOCABULARY_ID",
    )

    model_config = ConfigDict(populate_by_name=True)


class ConceptSetItem(BaseModel):
    """An individual item within a concept set expression."""
    concept: Optional[Concept] = None
    is_excluded: bool = Field(default=False, alias="isExcluded")
    include_mapped: bool = Field(default=False, alias="includeMapped")
    include_descendants: bool = Field(default=False, alias="includeDescendants")

    model_config = ConfigDict(populate_by_name=True)


class ConceptSetExpression(BaseModel):
    """Defines the inclusion criteria for a concept set, including explicit concepts and hierarchical relationships."""
    concept: Optional[Concept] = None
    is_excluded: bool = Field(default=False, alias="isExcluded")
    include_mapped: bool = Field(default=False, alias="includeMapped")
    include_descendants: bool = Field(default=False, alias="includeDescendants")
    items: Optional[List[ConceptSetItem]] = None

    model_config = ConfigDict(populate_by_name=True)


class ConceptSet(BaseModel):
    """A named set of concepts with an expression defining inclusion criteria."""
    id: int = Field(
        alias="id",
        validation_alias=AliasChoices("id", "ID"),
    )
    name: Optional[str] = Field(
        default=None,
        alias="name",
        validation_alias=AliasChoices("name", "NAME"),
    )
    expression: Optional[ConceptSetExpression] = Field(
        default=None,
        alias="expression",
        validation_alias=AliasChoices("expression", "EXPRESSION"),
    )

    model_config = ConfigDict(populate_by_name=True)


ConceptSet.model_rebuild()
