package com.main.common.loader;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class GameDataLoaderTest {

    private static final Path TEST_CARDS_FIXTURE = Path.of("src", "test", "resources", "static", "cards.json");

    @Test
    void shouldLoadAndGroupObjectsByTypeAcrossVersionsFromTestFixture() throws IOException {
        ObjectMapper objectMapper = mock(ObjectMapper.class);
        GameDataLoader gameDataLoader = new GameDataLoader(objectMapper);

        JsonNode fixtureRoot = readFixtureRootNode();
        when(objectMapper.readTree(any(InputStream.class))).thenReturn(fixtureRoot);

        gameDataLoader.load();

        assertThat(gameDataLoader.getObjectsByType("Item")).hasSize(2);
        assertThat(gameDataLoader.getObjectsByType("Hero")).hasSize(1);
        assertThat(gameDataLoader.getObjectsByType("UNKNOWN")).hasSize(1);
    }

    @Test
    void shouldReturnEmptyImmutableListWhenTypeDoesNotExist() {
        GameDataLoader gameDataLoader = new GameDataLoader(new ObjectMapper());
        JsonNode objectNode = new ObjectMapper().createObjectNode();

        List<JsonNode> unknownTypeObjects = gameDataLoader.getObjectsByType("MissingType");

        assertThat(unknownTypeObjects).isEmpty();
        assertThatThrownBy(() -> unknownTypeObjects.add(objectNode))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void shouldNotReloadWhenLoadIsCalledTwice() throws IOException {
        ObjectMapper objectMapper = mock(ObjectMapper.class);
        GameDataLoader gameDataLoader = new GameDataLoader(objectMapper);

        JsonNode fixtureRoot = readFixtureRootNode();
        when(objectMapper.readTree(any(InputStream.class))).thenReturn(fixtureRoot);

        gameDataLoader.load();

        verify(objectMapper, times(1)).readTree(any(InputStream.class));
        assertThat(gameDataLoader.getObjectsByType("Item")).hasSize(2);
        assertThat(gameDataLoader.getObjectsByType("Hero")).hasSize(1);
        assertThat(gameDataLoader.getObjectsByType("UNKNOWN")).hasSize(1);
    }

    private JsonNode readFixtureRootNode() throws IOException {
        ObjectMapper fixtureMapper = new ObjectMapper();

        try (InputStream fixture = Files.newInputStream(TEST_CARDS_FIXTURE)) {
            return fixtureMapper.readTree(fixture);
        }
    }

}
