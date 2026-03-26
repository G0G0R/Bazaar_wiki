package com.main.common.loader;

import java.util.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.main.wiki.item.dto.ItemJson;
import com.main.wiki.item.mapper.ItemMapper;
import com.main.wiki.item.model.Effect;
import com.main.wiki.item.model.Item;
import com.main.wiki.item.repository.EffectRepository;
import com.main.wiki.item.repository.ItemRepository;
import jakarta.transaction.Transactional;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class ItemLoader implements CommandLineRunner {

    private final GameDataLoader gameDataLoader;
    private final ObjectMapper objectMapper;
    private final ItemMapper itemMapper;
    private final ItemRepository itemRepository;
    private final EffectRepository effectRepository;

    public ItemLoader(GameDataLoader gameDataLoader, ObjectMapper objectMapper, ItemMapper itemMapper, ItemRepository itemRepository, EffectRepository effectRepository) {
        this.gameDataLoader = gameDataLoader;
        this.objectMapper = objectMapper;
        this.itemMapper = itemMapper;
        this.itemRepository = itemRepository;
        this.effectRepository = effectRepository;
    }

    @Transactional
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

            Item item = itemMapper.dtoToItem(dto);
            List<Effect> effectList = itemMapper.dtoToEffect(dto, item);
            item.setEffects(effectList);
            items.add(item);
        }

        itemRepository.deleteAll();
        effectRepository.deleteAll();
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

    public EffectRepository getEffectRepository() {
        return effectRepository;
    }
}