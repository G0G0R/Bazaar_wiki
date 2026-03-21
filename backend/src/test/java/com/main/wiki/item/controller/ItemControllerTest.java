package com.main.wiki.item.controller;

import com.main.common.util.HeroName;
import com.main.common.util.Tier;
import com.main.testutils.ItemBuilder;
import com.main.wiki.item.dto.ItemResponseDto;
import com.main.wiki.item.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ItemControllerTest {

    private ItemController itemController;
    private ItemService itemService;

    @BeforeEach
    void setUp() {
        itemService = mock(ItemService.class);
        itemController = new ItemController(itemService);
    }

    @Test
    void shouldReturnAllItems() {

        List<ItemResponseDto> expected = ItemBuilder.getListItemsDTO();

        when(itemService.getItems(null, null)).thenReturn(expected);
        List<ItemResponseDto> result = itemController.getItems(null, null);

        verify(itemService).getItems(null, null);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void shouldCallServiceWithParams() {
        when(itemService.getItems(Tier.BRONZE, HeroName.VANESSA.name())).thenReturn(List.of());

        itemController.getItems(Tier.BRONZE, HeroName.VANESSA.name());

        verify(itemService).getItems(Tier.BRONZE, HeroName.VANESSA.name());
    }

    @Test
    void shouldReturnItemById() {
        ItemResponseDto expected = ItemBuilder.getListItemsDTO().getFirst();

        when(itemService.getItemById("1")).thenReturn(expected);

        ItemResponseDto result = itemController.getItemById("1");

        verify(itemService).getItemById("1");

        assertThat(result).isEqualTo(expected);
    }
}