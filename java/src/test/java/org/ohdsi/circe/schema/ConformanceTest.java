package org.ohdsi.circe.schema;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.ohdsi.circe.schema.model.CohortExpression;
import org.ohdsi.circe.schema.util.FixtureLoader;

import java.io.File;

public class ConformanceTest {

    private static final String FIXTURES_DIR = FixtureLoader.findFixturesDir();

    private void assertRoundTrip(String fixtureName) throws Exception {
        String path = FIXTURES_DIR + File.separator + fixtureName;
        JsonNode root = FixtureLoader.loadFixture(path);
        JsonNode expressionData = root.get("CohortExpression");
        String json = expressionData.toString();

        CohortExpression cohort = CohortExpression.fromJson(json);
        String serialized = cohort.toJson();

        // Re-parse
        CohortExpression cohort2 = CohortExpression.fromJson(serialized);

        Assertions.assertNotNull(cohort2);
        Assertions.assertEquals(
            cohort.title, cohort2.title,
            "Title mismatch for " + fixtureName
        );

        int csCount = cohort.conceptSets != null ? cohort.conceptSets.size() : 0;
        int csCount2 = cohort2.conceptSets != null ? cohort2.conceptSets.size() : 0;
        Assertions.assertEquals(csCount, csCount2, "Concept set count mismatch for " + fixtureName);

        int irCount = cohort.inclusionRules != null ? cohort.inclusionRules.size() : 0;
        int irCount2 = cohort2.inclusionRules != null ? cohort2.inclusionRules.size() : 0;
        Assertions.assertEquals(irCount, irCount2, "Inclusion rule count mismatch for " + fixtureName);

        // Verify concept set IDs preserved
        if (cohort.conceptSets != null && cohort2.conceptSets != null) {
            for (int i = 0; i < Math.min(cohort.conceptSets.size(), cohort2.conceptSets.size()); i++) {
                Assertions.assertEquals(
                    cohort.conceptSets.get(i).id,
                    cohort2.conceptSets.get(i).id,
                    "ConceptSet id mismatch for " + fixtureName + " at index " + i
                );
            }
        }

        // Verify inclusion rule names preserved
        if (cohort.inclusionRules != null && cohort2.inclusionRules != null) {
            for (int i = 0; i < Math.min(cohort.inclusionRules.size(), cohort2.inclusionRules.size()); i++) {
                Assertions.assertEquals(
                    cohort.inclusionRules.get(i).name,
                    cohort2.inclusionRules.get(i).name,
                    "InclusionRule name mismatch for " + fixtureName + " at index " + i
                );
            }
        }
    }

    @Test
    public void testMinimalCohort() throws Exception {
        assertRoundTrip("minimal-cohort.json");
    }

    @Test
    public void testMultiCriteriaCohort() throws Exception {
        assertRoundTrip("multi-criteria-cohort.json");
    }

    @Test
    public void testWithInclusionRules() throws Exception {
        assertRoundTrip("with-inclusion-rules.json");
    }

    @Test
    public void testWithCensoring() throws Exception {
        assertRoundTrip("with-censoring.json");
    }

    @Test
    public void testWithWindowedCriteria() throws Exception {
        assertRoundTrip("with-windowed-criteria.json");
    }

    @Test
    public void testWithCustomEra() throws Exception {
        assertRoundTrip("with-custom-era.json");
    }
}
