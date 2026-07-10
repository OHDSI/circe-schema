from typing import Any, Dict, Optional

CRITERIA_REGISTRY: Dict[str, Any] = {}


def register_criteria(name: str):
    """Decorator that registers a criteria class by its JSON type name.

    Usage::

        @register_criteria("MyCustomType")
        class MyCustomType(Criteria):
            ...
    """
    def decorator(cls):
        CRITERIA_REGISTRY[name] = cls
        return cls
    return decorator


def get_criteria_class(name: str) -> Optional[Any]:
    """Look up a criteria class by its JSON type name."""
    return CRITERIA_REGISTRY.get(name)


def get_all_criteria() -> Dict[str, Any]:
    """Return a copy of the full criteria registry.

    Deserializers use this to map JSON type names to Python classes.
    """
    return dict(CRITERIA_REGISTRY)
