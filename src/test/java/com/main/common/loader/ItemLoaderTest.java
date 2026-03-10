package com.main.common.loader;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.main.wiki.dto.ItemJson;
import com.main.wiki.mapper.ItemMapper;
import com.main.wiki.model.Item;
import com.main.wiki.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ItemLoaderTest {

    @Test
    void shouldLoadConvertFilterAndPersistItems() throws Exception {
        GameDataLoader gameDataLoader = mock(GameDataLoader.class);
        ObjectMapper objectMapper = mock(ObjectMapper.class);
        ItemMapper itemMapper = mock(ItemMapper.class);
        ItemRepository itemRepository = mock(ItemRepository.class);

        ItemLoader itemLoader = new ItemLoader(gameDataLoader, objectMapper, itemMapper, itemRepository);

        JsonNode firstNode = mock(JsonNode.class);
        JsonNode secondNode = mock(JsonNode.class);
        JsonNode thirdNode = mock(JsonNode.class);
        when(gameDataLoader.getObjectsByType("Item")).thenReturn(List.of(firstNode, secondNode, thirdNode));

        ItemJson firstDto = new ItemJson();
        firstDto.setHeroes(List.of("HeroA"));
        ItemJson secondDto = new ItemJson();
        secondDto.setHeroes(List.of("HeroA", "HeroB"));
        ItemJson thirdDto = new ItemJson();
        thirdDto.setHeroes(null);

        when(objectMapper.convertValue(firstNode, ItemJson.class)).thenReturn(firstDto);
        when(objectMapper.convertValue(secondNode, ItemJson.class)).thenReturn(secondDto);
        when(objectMapper.convertValue(thirdNode, ItemJson.class)).thenReturn(thirdDto);

        Item mappedFirst = new Item();
        mappedFirst.setId("item-1");
        when(itemMapper.toEntity(firstDto)).thenReturn(mappedFirst);
        when(itemMapper.toEntity(thirdDto)).thenReturn(null);

        itemLoader.run();

        verify(gameDataLoader).load();
        verify(itemMapper).toEntity(firstDto);
        verify(itemMapper, never()).toEntity(secondDto);
        verify(itemMapper).toEntity(thirdDto);

        InOrder inOrder = inOrder(itemRepository);
        inOrder.verify(itemRepository).deleteAll();
        inOrder.verify(itemRepository).saveAll(argThat(items -> toList(items).contains(mappedFirst)));
    }

    @Test
    void shouldDeleteAndSaveEmptyListWhenNoItemsFound() throws Exception {
        GameDataLoader gameDataLoader = mock(GameDataLoader.class);
        ObjectMapper objectMapper = mock(ObjectMapper.class);
        ItemMapper itemMapper = mock(ItemMapper.class);
        ItemRepository itemRepository = mock(ItemRepository.class);

        ItemLoader itemLoader = new ItemLoader(gameDataLoader, objectMapper, itemMapper, itemRepository);
        when(gameDataLoader.getObjectsByType("Item")).thenReturn(List.of());

        itemLoader.run();

        verify(gameDataLoader).load();
        verify(itemRepository).deleteAll();
        verify(itemRepository).saveAll(argThat(items -> toList(items).isEmpty()));
    }

    private static List<Item> toList(Iterable<Item> items) {
        List<Item> result = new ArrayList<>();
        items.forEach(result::add);
        return result;
    }

}
