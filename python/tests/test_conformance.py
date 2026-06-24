"""Conformance tests for the Python circe-schema implementation.

Each test validates that a fixture JSON can be:
1. Parsed into a CohortExpression
2. Serialized back to JSON
3. The re-serialized JSON parses again into an equivalent object
"""

import json

from circe_schema import CohortExpression


def roundtrip(fixture_data: dict):
    """Parse, serialize, re-parse and verify structural equivalence."""
    expression_data = fixture_data["CohortExpression"]

    # First parse
    cohort = CohortExpression.model_validate(expression_data, strict=False)

    # Serialize to JSON
    json_str = cohort.model_dump_json()
    roundtrip_data = json.loads(json_str)

    # Second parse
    cohort2 = CohortExpression.model_validate(roundtrip_data, strict=False)

    # Verify structural equivalence
    assert cohort.title == cohort2.title, f"Title mismatch: {cohort.title} != {cohort2.title}"
    assert (cohort.primary_criteria is None) == (cohort2.primary_criteria is None)
    assert (cohort.additional_criteria is None) == (cohort2.additional_criteria is None)
    assert len(cohort.concept_sets) == len(cohort2.concept_sets), "Concept set count mismatch"
    assert len(cohort.inclusion_rules) == len(cohort2.inclusion_rules), "Inclusion rule count mismatch"
    assert len(cohort.censoring_criteria) == len(cohort2.censoring_criteria), "Censoring criteria count mismatch"
    assert (cohort.end_strategy is None) == (cohort2.end_strategy is None)

    # Verify concept set IDs preserved
    for cs1, cs2 in zip(cohort.concept_sets, cohort2.concept_sets):
        assert cs1.id == cs2.id, f"ConceptSet id mismatch: {cs1.id} != {cs2.id}"
        assert cs1.name == cs2.name, f"ConceptSet name mismatch: {cs1.name} != {cs2.name}"

    # Verify inclusion rule names preserved
    for ir1, ir2 in zip(cohort.inclusion_rules, cohort2.inclusion_rules):
        assert ir1.name == ir2.name, f"InclusionRule name mismatch"

    return True


def test_minimal_cohort(minimal_cohort):
    assert roundtrip(minimal_cohort)


def test_multi_criteria_cohort(multi_criteria_cohort):
    assert roundtrip(multi_criteria_cohort)


def test_with_inclusion_rules(with_inclusion_rules):
    assert roundtrip(with_inclusion_rules)


def test_with_censoring(with_censoring):
    assert roundtrip(with_censoring)


def test_with_windowed_criteria(with_windowed_criteria):
    assert roundtrip(with_windowed_criteria)


def test_with_custom_era(with_custom_era):
    assert roundtrip(with_custom_era)
