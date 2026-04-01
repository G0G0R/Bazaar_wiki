package com.main.wiki.item.controller;

import org.springframework.web.bind.annotation.*;

import com.main.common.util.Size;
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
    public List<ItemResponseDto> getItems() {
        return itemService.getAllItems();
    }

    @GetMapping("/id/{id}")
    public ItemResponseDto getItemById(@PathVariable String id) {
        return itemService.getItemById(id);
    }

    @GetMapping("/tier/{tier}")
    public List<ItemResponseDto> getItemByTier(@PathVariable Tier tier) {
        return itemService.getItemByTier(tier);
    }

    @GetMapping("/hero/{hero}")
    public List<ItemResponseDto> getItemByHero(@PathVariable String hero) {
        return itemService.getItemByHero(hero);
    }

    @GetMapping("/size/{size}")
    public List<ItemResponseDto> getItemBySize(@PathVariable Size size) {
        return itemService.getItemBySize(size);
    }

    @GetMapping("/tags")
    public List<ItemResponseDto> getItemByTags(@RequestParam List<String> tags) {
        return itemService.getItemByTags(tags);
    }
}