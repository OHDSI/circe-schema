package org.ohdsi.circe.cohortdefinition;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public final class CriteriaRegistry {

    private static final Map<String, Class<? extends Criteria>> REGISTRY = new LinkedHashMap<>();

    static void add(String name, Class<? extends Criteria> clazz) {
        REGISTRY.put(name, clazz);
    }

    public static Class<? extends Criteria> get(String name) {
        return REGISTRY.get(name);
    }

    public static boolean isRegistered(String name) {
        return REGISTRY.containsKey(name);
    }

    public static Collection<Class<? extends Criteria>> getAll() {
        return Collections.unmodifiableCollection(REGISTRY.values());
    }

    private CriteriaRegistry() {
    }
}
