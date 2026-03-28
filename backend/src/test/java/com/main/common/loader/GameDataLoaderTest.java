package com.main.common.loader;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.main.testutils.JsonTestLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameDataLoaderTest {

    private static final String JSON_PATH = "cards-test.json";
    private static final String JSON_MISSING_TYPE_PATH = "missing-type.json";

    private ObjectMapper objectMapper;
    private GameDataLoader gameDataLoader;
    private Resource resource;

    @BeforeEach
    void setUp() {
        objectMapper = mock(ObjectMapper.class);
        resource = mock(Resource.class);
        gameDataLoader = new GameDataLoader(objectMapper, resource);
    }

    /*
     * Test loading cards-test.json and grouping objects by type
     */
    @Test
    void shouldLoadAndGroupObjectsByType() throws IOException {

        JsonNode json = JsonTestLoader.load(JSON_PATH);

        when(resource.getInputStream()).thenReturn(JsonTestLoader.getInputStream(JSON_PATH));
        doReturn(json).when(objectMapper).readTree(any(InputStream.class));

        gameDataLoader.load();

        assertThat(gameDataLoader.getObjectsByType("Item")).hasSize(3);
        assertThat(gameDataLoader.getObjectsByType("Hero")).hasSize(1);
    }

    /*
     * Test with missing type data
     * Should return UNKNOWN type
     */
    @Test
    void shouldHandleMissingTypeField() throws IOException {

        JsonNode json = JsonTestLoader.load(JSON_MISSING_TYPE_PATH);

        when(resource.getInputStream()).thenReturn(JsonTestLoader.getInputStream(JSON_MISSING_TYPE_PATH));
        doReturn(json).when(objectMapper).readTree(any(InputStream.class));

        gameDataLoader.load();

        assertThat(gameDataLoader.getObjectsByType("Item")).hasSize(1);
        assertThat(gameDataLoader.getObjectsByType("UNKNOWN")).hasSize(1);
    }
}