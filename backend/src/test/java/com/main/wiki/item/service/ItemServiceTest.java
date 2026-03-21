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
    private ItemResponseMapper mapper;
    private ItemService itemService;

    @BeforeEach
    void setUp() {
        itemRepository = mock(ItemRepository.class);
        mapper = new ItemResponseMapper();
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
        Item item = ItemBuilder.getItem();

        when(itemRepository.findById(anyString())).thenReturn(Optional.of(item));

        ItemResponseDto result = itemService.getItemById(item.getId());

        verify(itemRepository).findById(item.getId());
        assertThat(result).isEqualTo(mapper.toDto(item));
    }

    @Test
    void shouldThrowWhenItemNotFound() {
        when(itemRepository.findById("999")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> itemService.getItemById("999")).isInstanceOf(RuntimeException.class);
    }

    // =========================
    // getItems (filters)
    // =========================
    @Test
    void shouldReturnAllItemsWhenNoFilters() {
        List<ItemResponseDto> expected = ItemBuilder.getListItemsDTO();

        when(itemRepository.findAll()).thenReturn(ItemBuilder.getListItem());

        List<ItemResponseDto> result = itemService.getItems(null, null);

        verify(itemRepository).findAll();
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void shouldFilterByTier() {
        List<ItemResponseDto> expected = ItemBuilder.getListItemsDTO();

        when(itemRepository.findByTier(Tier.BRONZE)).thenReturn(ItemBuilder.getListItem());

        List<ItemResponseDto> result = itemService.getItems(Tier.BRONZE, null);

        verify(itemRepository).findByTier(Tier.BRONZE);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void shouldFilterByHero() {
        List<ItemResponseDto> expected = ItemBuilder.getListItemsDTO();

        when(itemRepository.findByHero(HeroName.VANESSA.name())).thenReturn(ItemBuilder.getListItem());

        List<ItemResponseDto> result = itemService.getItems(null, HeroName.VANESSA.name());

        verify(itemRepository).findByHero(HeroName.VANESSA.name());
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void shouldFilterByTierAndHero() {
        List<ItemResponseDto> expected = ItemBuilder.getListItemsDTO();

        when(itemRepository.findByTierAndHero(Tier.BRONZE, HeroName.VANESSA.name())).thenReturn(ItemBuilder.getListItem());

        List<ItemResponseDto> result = itemService.getItems(Tier.BRONZE, HeroName.VANESSA.name());

        verify(itemRepository).findByTierAndHero(Tier.BRONZE, HeroName.VANESSA.name());
        assertThat(result).isEqualTo(expected);
    }
}