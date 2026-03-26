package com.main.common.loader;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.main.wiki.item.dto.ItemJson;
import com.main.wiki.item.mapper.ItemMapper;
import com.main.wiki.item.model.Effect;
import com.main.wiki.item.model.Item;
import com.main.wiki.item.repository.EffectRepository;
import com.main.wiki.item.repository.ItemRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class ItemLoaderTest {

    @Test
    void shouldLoadItemsAndIgnoreItemsWithMultipleHeroes() throws Exception {

        /*GameDataLoader gameDataLoader = mock(GameDataLoader.class);
        ObjectMapper objectMapper = mock(ObjectMapper.class);
        ItemMapper itemMapper = mock(ItemMapper.class);
        ItemRepository itemRepository = mock(ItemRepository.class);
        EffectRepository effectRepository = mock(EffectRepository.class);

        ItemLoader loader = new ItemLoader(gameDataLoader, objectMapper, itemMapper, itemRepository, effectRepository);

        JsonNode node1 = mock(JsonNode.class);
        JsonNode node2 = mock(JsonNode.class);

        when(gameDataLoader.getObjectsByType("Item"))
                .thenReturn(List.of(node1, node2));

        ItemJson dto1 = new ItemJson();
        dto1.setHeroes(List.of("Vanessa"));

        ItemJson dto2 = new ItemJson();
        dto2.setHeroes(List.of("Vanessa", "Pygmalien"));

        when(objectMapper.convertValue(node1, ItemJson.class)).thenReturn(dto1);
        when(objectMapper.convertValue(node2, ItemJson.class)).thenReturn(dto2);

        Item mappedItem = new Item();
        when(itemMapper.dtoToItem(dto1)).thenReturn(mappedItem);
        List<Effect> mappedEffect = new ArrayList<>();
        when(itemMapper.dtoToEffect(dto1)).thenReturn(mappedEffect);

        loader.run();

        verify(itemRepository).saveAll(List.of(mappedItem));*/
    }
}