package com.main.common.loader;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.main.common.util.Constants;
import com.main.common.util.Tier;
import com.main.wiki.dto.ItemJson;
import com.main.wiki.model.Item;
import com.main.wiki.repository.ItemRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class GameDataLoader implements CommandLineRunner {

    private final ItemRepository itemRepository;
    private final ObjectMapper mapper;

    public GameDataLoader(ItemRepository itemRepository, ObjectMapper mapper) {
        this.itemRepository = itemRepository;
        this.mapper = mapper;
    }

    @Override
    public void run(String @NonNull  ... args) throws Exception {
        loadItems();
    }

    private void loadItems() throws Exception {

        itemRepository.deleteAllInBatch();

        InputStream is = new ClassPathResource(Constants.BAZAAR_JSON).getInputStream();

        JsonNode root = mapper.readTree(is);

        // récupérer la version (ex: "5.0.0")
        JsonNode cardsNode = root.elements().next();

        ItemJson[] itemsJson = mapper.treeToValue(cardsNode, ItemJson[].class);

        List<Item> items = new ArrayList<>();

        for (ItemJson json : itemsJson) {

            String name = null;

            if (json.getLocalization() != null
                    && json.getLocalization().getTitle() != null) {

                name = json.getLocalization().getTitle().getText();
            }

            Tier tier = null;

            if (json.getStartingTier() != null) {
                tier = Tier.valueOf(json.getStartingTier().toUpperCase());
            }

            Item item = new Item(
                    json.getId(),
                    name,
                    tier
            );

            items.add(item);
        }

        itemRepository.saveAll(items);

        System.out.println("Items loaded: " + items.size());
    }
}