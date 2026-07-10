package org.ohdsi.circe.vocabulary;

import java.util.Objects;

public class ConceptSet {
    public int id;
    public String name;
    public ConceptSetExpression expression;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConceptSet)) return false;
        ConceptSet other = (ConceptSet) o;
        return Objects.equals(id, other.id)
            && Objects.equals(name, other.name)
            && Objects.equals(expression, other.expression);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, expression);
    }
}
