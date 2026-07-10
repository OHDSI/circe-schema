package org.ohdsi.circe.cohortdefinition;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class FixtureLoader {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static JsonNode loadFixture(String fixturePath) throws IOException {
        File file = new File(fixturePath);
        if (!file.exists()) {
            throw new IOException("Fixture not found: " + fixturePath);
        }
        return MAPPER.readTree(file);
    }

    public static String findFixturesDir() {
        String cwd = System.getProperty("user.dir");
        File candidate = new File(cwd, "../fixtures");
        if (candidate.exists()) return candidate.getAbsolutePath();
        candidate = new File(cwd, "../../fixtures");
        if (candidate.exists()) return candidate.getAbsolutePath();
        candidate = new File(cwd, "fixtures");
        if (candidate.exists()) return candidate.getAbsolutePath();
        return cwd;
    }
}
