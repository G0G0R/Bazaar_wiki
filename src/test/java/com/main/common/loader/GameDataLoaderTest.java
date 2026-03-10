package com.main.common.loader;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.main.testutils.JsonFixtureLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameDataLoaderTest {

    private static final String FIXTURE_PATH = "/static/cards.json";

    private ObjectMapper objectMapper;
    private GameDataLoader gameDataLoader;

    @BeforeEach
    void setUp() {
        objectMapper = mock(ObjectMapper.class);
        gameDataLoader = new GameDataLoader(objectMapper);
    }

    @Test
    void shouldLoadAndGroupObjectsByTypeAcrossVersionsFromTestFixture() throws IOException {

        JsonNode fixtureRoot = readFixtureRootNode();

        when(objectMapper.readTree(any(InputStream.class))).thenReturn(fixtureRoot);

        gameDataLoader.load();

        assertThat(gameDataLoader.getObjectsByType("Item")).hasSize(2);
        assertThat(gameDataLoader.getObjectsByType("Hero")).hasSize(1);
        assertThat(gameDataLoader.getObjectsByType("UNKNOWN")).hasSize(1);
    }

    @Test
    void shouldReturnEmptyImmutableListWhenTypeDoesNotExist() {

        List<JsonNode> unknownTypeObjects =
                gameDataLoader.getObjectsByType("MissingType");

        assertThat(unknownTypeObjects).isEmpty();

        JsonNode node = objectMapper.createObjectNode();

        assertThatThrownBy(() -> unknownTypeObjects.add(node))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void shouldNotReloadWhenLoadIsCalledTwice() throws IOException {

        JsonNode fixtureRoot = readFixtureRootNode();

        when(objectMapper.readTree(any(InputStream.class))).thenReturn(fixtureRoot);

        gameDataLoader.load();
        gameDataLoader.load();

        verify(objectMapper, times(1)).readTree(any(InputStream.class));

        assertThat(gameDataLoader.getObjectsByType("Item")).hasSize(2);
        assertThat(gameDataLoader.getObjectsByType("Hero")).hasSize(1);
        assertThat(gameDataLoader.getObjectsByType("UNKNOWN")).hasSize(1);
    }

    @Test
    void shouldHandleMissingTypeField() throws IOException {

        GameDataLoader loader = new GameDataLoader(objectMapper);

        JsonNode root = JsonFixtureLoader.load("missing-type.json");

        when(objectMapper.readTree(any(InputStream.class)))
                .thenReturn(root);

        loader.load();

        assertThat(loader.getObjectsByType("Item")).hasSize(1);
        assertThat(loader.getObjectsByType("UNKNOWN")).hasSize(1);
    }

    private JsonNode readFixtureRootNode() throws IOException {

        ObjectMapper fixtureMapper = new ObjectMapper();

        try (InputStream fixture =
                     getClass().getResourceAsStream(FIXTURE_PATH)) {

            assertThat(fixture).isNotNull();

            return fixtureMapper.readTree(fixture);
        }
    }
}