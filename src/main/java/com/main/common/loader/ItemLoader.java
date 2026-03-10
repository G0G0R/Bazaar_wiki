package com.main.common.loader;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.main.wiki.dto.ItemJson;
import com.main.wiki.mapper.ItemMapper;
import com.main.wiki.model.Item;
import com.main.wiki.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ItemLoader implements CommandLineRunner {

    private final GameDataLoader gameDataLoader;
    private final ObjectMapper objectMapper;
    private final ItemMapper itemMapper;
    private final ItemRepository itemRepository;

    @Override
    public void run(String... args) throws Exception {

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
}