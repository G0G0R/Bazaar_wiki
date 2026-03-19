package com.main.common.loader;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.main.wiki.item.dto.ItemJson;
import com.main.wiki.item.mapper.ItemMapper;
import com.main.wiki.item.model.Item;
import com.main.wiki.item.repository.ItemRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ItemLoader implements CommandLineRunner {

    private final GameDataLoader gameDataLoader;
    private final ObjectMapper objectMapper;
    private final ItemMapper itemMapper;
    private final ItemRepository itemRepository;

    public ItemLoader(GameDataLoader gameDataLoader, ObjectMapper objectMapper, ItemMapper itemMapper, ItemRepository itemRepository) {
        this.gameDataLoader = gameDataLoader;
        this.objectMapper = objectMapper;
        this.itemMapper = itemMapper;
        this.itemRepository = itemRepository;
    }

    @Override
    public void run(String @NonNull ... args) throws Exception {

        gameDataLoader.load();

        List<JsonNode> itemNodes = gameDataLoader.getObjectsByType("Item");

        List<Item> items = new ArrayList<>();

        for (JsonNode node : itemNodes) {

            ItemJson dto = objectMapper.convertValue(node, ItemJson.class);

            // ignorer les items ayant plusieurs héros
            if (dto.getHeroes() != null && dto.getHeroes().size() > 1) {
                continue;
            }

            Item item = itemMapper.toEntity(dto);

            if (item != null) {
                items.add(item);
            }
        }

        itemRepository.deleteAll();
        itemRepository.saveAll(items);
    }

    public GameDataLoader getGameDataLoader() {
        return gameDataLoader;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public ItemMapper getItemMapper() {
        return itemMapper;
    }

    public ItemRepository getItemRepository() {
        return itemRepository;
    }
}