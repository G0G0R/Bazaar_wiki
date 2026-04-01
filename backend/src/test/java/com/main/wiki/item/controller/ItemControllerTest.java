package com.main.wiki.item.controller;

import com.main.common.util.Size;
import com.main.common.util.Tier;
import com.main.testutils.ItemBuilder;
import com.main.wiki.item.dto.ItemResponseDto;
import com.main.wiki.item.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

class ItemControllerTest {

    private ItemController itemController;
    private ItemService itemService;

    @BeforeEach
    void setUp() {
        itemService = mock(ItemService.class);
        itemController = new ItemController(itemService);
    }

    @Test
    void getItems_shouldReturnAllItems() {
        List<ItemResponseDto> expected = ItemBuilder.getListItemsDTO();

        when(itemService.getAllItems()).thenReturn(expected);

        List<ItemResponseDto> result = itemController.getItems();

        verify(itemService).getAllItems();
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void getItemById_shouldReturnMatchingItem() {
        ItemResponseDto expected = ItemBuilder.getListItemsDTO().getFirst();

        when(itemService.getItemById("1")).thenReturn(expected);

        ItemResponseDto result = itemController.getItemById("1");

        verify(itemService).getItemById("1");
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void getItemByTier_shouldReturnFilteredItems() {
        List<ItemResponseDto> expected = List.of(ItemBuilder.getListItemsDTO().getFirst());

        when(itemService.getItemByTier(Tier.BRONZE)).thenReturn(expected);

        List<ItemResponseDto> result = itemController.getItemByTier(Tier.BRONZE);

        verify(itemService).getItemByTier(Tier.BRONZE);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void getItemByHero_shouldReturnFilteredItems() {
        List<ItemResponseDto> expected = List.of(
                ItemBuilder.getListItemsDTO().get(1),
                ItemBuilder.getListItemsDTO().get(2)
        );

        when(itemService.getItemByHero("VANESSA")).thenReturn(expected);

        List<ItemResponseDto> result = itemController.getItemByHero("VANESSA");

        verify(itemService).getItemByHero("VANESSA");
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void getItemBySize_shouldReturnFilteredItems() {
        List<ItemResponseDto> expected = List.of(ItemBuilder.getListItemsDTO().getFirst());

        when(itemService.getItemBySize(Size.SMALL)).thenReturn(expected);

        List<ItemResponseDto> result = itemController.getItemBySize(Size.SMALL);

        verify(itemService).getItemBySize(Size.SMALL);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void getItemByTags_shouldReturnFilteredItems() {
        List<String> tags = List.of("Burn", "Weapon");
        List<ItemResponseDto> expected = List.of(ItemBuilder.getListItemsDTO().get(1));

        when(itemService.getItemByTags(tags)).thenReturn(expected);

        List<ItemResponseDto> result = itemController.getItemByTags(tags);

        verify(itemService).getItemByTags(tags);
        assertThat(result).isEqualTo(expected);
    }
}