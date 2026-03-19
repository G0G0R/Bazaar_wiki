package com.main.common.loader;

import java.util.*;
import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.main.common.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;


@Component
public class GameDataLoader {

    private static final Logger log = LoggerFactory.getLogger(GameDataLoader.class);

    private final ObjectMapper objectMapper;

    private final Map<String, List<JsonNode>> objectsByType = new HashMap<>();
    private final Resource resource;

    @Autowired
    public GameDataLoader(ObjectMapper objectMapper) {
        this(objectMapper, new ClassPathResource(Constants.BAZAAR_JSON));
    }

    public GameDataLoader(ObjectMapper objectMapper, Resource resource) {
        this.objectMapper = objectMapper;
        this.resource = resource;
    }

    public void load() throws IOException {

        if (!objectsByType.isEmpty()) {
            return;
        }

        InputStream is = resource.getInputStream();

        JsonNode root = objectMapper.readTree(is);

        for (JsonNode versionNode : root) {

            for (JsonNode cardNode : versionNode) {

                String type = cardNode.path("Type").asText("UNKNOWN");

                objectsByType
                        .computeIfAbsent(type, _ -> new ArrayList<>())
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