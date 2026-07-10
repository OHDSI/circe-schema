package org.ohdsi.circe.vocabulary;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ohdsi.analysis.Utils;
import java.util.Arrays;
import java.util.Objects;

public class ConceptSetExpression {

    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();

    public static class ConceptSetItem {
        public Concept concept;
        public boolean isExcluded;
        public boolean includeDescendants;
        public boolean includeMapped;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof ConceptSetItem)) return false;
            ConceptSetItem other = (ConceptSetItem) o;
            return Objects.equals(concept, other.concept)
                && Objects.equals(isExcluded, other.isExcluded)
                && Objects.equals(includeDescendants, other.includeDescendants)
                && Objects.equals(includeMapped, other.includeMapped);
        }

        @Override
        public int hashCode() {
            return Objects.hash(concept, isExcluded, includeDescendants, includeMapped);
        }
    }

    public ConceptSetItem[] items;

    public static ConceptSetExpression fromJson(String json) {
        return Utils.deserialize(json, ConceptSetExpression.class);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConceptSetExpression)) return false;
        ConceptSetExpression other = (ConceptSetExpression) o;
        return Arrays.equals(items, other.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash((Object[]) items);
    }
}
