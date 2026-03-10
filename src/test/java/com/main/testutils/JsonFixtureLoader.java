package com.main.testutils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;

public class JsonFixtureLoader {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static JsonNode load(String filename) {

        try (InputStream is =
                     JsonFixtureLoader.class
                             .getClassLoader()
                             .getResourceAsStream("fixtures/" + filename)) {

            if (is == null) {
                throw new RuntimeException("Fixture not found: " + filename);
            }

            return mapper.readTree(is);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}