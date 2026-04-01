package com.main.wiki.item.service;

import com.main.common.util.HeroName;
import com.main.common.util.Tier;
import com.main.testutils.ItemBuilder;
import com.main.wiki.item.dto.ItemResponseDto;
import com.main.wiki.item.mapper.ItemResponseMapper;
import com.main.wiki.item.model.Item;
import com.main.wiki.item.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class ItemServiceTest {

    private ItemRepository itemRepository;
    private ItemService itemService;

    @BeforeEach
    void setUp() {
        itemRepository = mock(ItemRepository.class);
        ItemResponseMapper mapper = new ItemResponseMapper();
        itemService = new ItemService(itemRepository, mapper);
    }

    // =========================
    // getAllItems
    // =========================
    @Test
    void shouldReturnAllItems() {
        List<ItemResponseDto> expected = ItemBuilder.getListItemsDTO();

        when(itemRepository.findAll()).thenReturn(ItemBuilder.getListItem());

        List<ItemResponseDto> result = itemService.getAllItems();

        verify(itemRepository).findAll();
        assertThat(result).isEqualTo(expected);
    }

    // =========================
    // getItemById
    // =========================
    @Test
    void shouldReturnItemById() {
        Item item = ItemBuilder.getListItem().getFirst();
        ItemResponseDto expected = ItemBuilder.getListItemsDTO().getFirst();

        when(itemRepository.findById("1")).thenReturn(Optional.of(item));

        ItemResponseDto result = itemService.getItemById("1");

        verify(itemRepository).findById("1");
        assertThat(result).isEqualTo(expected);
    }

    // =========================
    // get item by id should return runtime exception when no item found
    // =========================
    @Test
    void shouldThrowWhenItemNotFound() {
        when(itemRepository.findById("999")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> itemService.getItemById("999")).isInstanceOf(RuntimeException.class);
    }

    // =========================
    // get items by tier
    // =========================
    @Test
    void shouldFilterByTier() {
        List<Item> items = List.of(ItemBuilder.getListItem().getFirst());
        List<ItemResponseDto> expected = List.of(ItemBuilder.getListItemsDTO().getFirst());

        when(itemRepository.findByTier(Tier.BRONZE)).thenReturn(items);

        List<ItemResponseDto> result = itemService.getItemByTier(Tier.BRONZE);

        verify(itemRepository).findByTier(Tier.BRONZE);
        assertThat(result).isEqualTo(expected);
    }

    // =========================
    // get items by hero
    // =========================
    @Test
    void shouldFilterByHero() {
        List<Item> items = List.of(ItemBuilder.getListItem().get(1), ItemBuilder.getListItem().get(2));
        List<ItemResponseDto> expected = List.of(
                ItemBuilder.getListItemsDTO().get(1),
                ItemBuilder.getListItemsDTO().get(2)
        );

        when(itemRepository.findByHero(HeroName.VANESSA.name())).thenReturn(items);

        List<ItemResponseDto> result = itemService.getItemByHero(HeroName.VANESSA.name());

        verify(itemRepository).findByHero(HeroName.VANESSA.name());
        assertThat(result).isEqualTo(expected);
    }

    // =========================
    // get items by tags
    // =========================
    @Test
    void shouldFilterByTags() {
        List<String> tags = List.of("Burn", "Weapon");

        List<Item> items = List.of(ItemBuilder.getListItem().get(1));
        List<ItemResponseDto> expected = List.of(ItemBuilder.getListItemsDTO().get(1));

        when(itemRepository.findByTags(tags)).thenReturn(items);

        List<ItemResponseDto> result = itemService.getItemByTags(tags);

        verify(itemRepository).findByTags(tags);
        assertThat(result).isEqualTo(expected);
    }
}