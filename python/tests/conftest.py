import json
from pathlib import Path

import pytest

FIXTURES_DIR = Path(__file__).resolve().parent.parent.parent / "fixtures"


def load_fixture(name: str) -> dict:
    path = FIXTURES_DIR / name
    if not path.exists():
        pytest.skip(f"Fixture not found: {path}")
    with open(path) as f:
        return json.load(f)


@pytest.fixture
def minimal_cohort():
    return load_fixture("minimal-cohort.json")


@pytest.fixture
def multi_criteria_cohort():
    return load_fixture("multi-criteria-cohort.json")


@pytest.fixture
def with_inclusion_rules():
    return load_fixture("with-inclusion-rules.json")


@pytest.fixture
def with_censoring():
    return load_fixture("with-censoring.json")


@pytest.fixture
def with_windowed_criteria():
    return load_fixture("with-windowed-criteria.json")


@pytest.fixture
def with_custom_era():
    return load_fixture("with-custom-era.json")
