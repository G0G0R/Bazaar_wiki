package com.main.wiki.item.controller;

import org.springframework.web.bind.annotation.*;
import com.main.common.util.Tier;
import com.main.wiki.item.dto.ItemResponseDto;
import com.main.wiki.item.service.ItemService;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<ItemResponseDto> getAllItems() {
        return itemService.getAllItems();
    }

    @GetMapping("/{id}")
    public ItemResponseDto getItemById(@PathVariable String id) {
        return itemService.getItemById(id);
    }

    @GetMapping("/tier/{tier}")
    public List<ItemResponseDto> getItemsByTier(@PathVariable Tier tier) {
        return itemService.getItemsByTier(tier);
    }

    @GetMapping("/hero/{hero}")
    public List<ItemResponseDto> getItemsByHero(@PathVariable String hero) {
        return itemService.getItemsByHero(hero);
    }
}