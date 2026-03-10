package com.main.common.loader;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class GameDataLoader {

    private static final Logger log = LoggerFactory.getLogger(GameDataLoader.class);

    private final ObjectMapper objectMapper;

    @Getter
    private final Map<String, List<JsonNode>> objectsByType = new HashMap<>();

    public void load() throws IOException {

        if (!objectsByType.isEmpty()) {
            return;
        }

        InputStream is = new ClassPathResource("static/cards.json").getInputStream();

        JsonNode root = objectMapper.readTree(is);

        for (JsonNode versionNode : root) {

            for (JsonNode cardNode : versionNode) {

                String type = cardNode.path("Type").asText("UNKNOWN");

                objectsByType
                        .computeIfAbsent(type, k -> new ArrayList<>())
                        .add(cardNode);
            }
        }

        objectsByType.forEach((type, list) ->
                log.info("Loaded {} objects of type {}", list.size(), type)
        );
    }

    public List<JsonNode> getObjectsByType(String type) {
        return objectsByType.getOrDefault(type, Collections.emptyList());
    }
}