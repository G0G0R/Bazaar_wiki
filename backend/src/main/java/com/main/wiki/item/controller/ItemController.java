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
    public List<ItemResponseDto> getItems(
            @RequestParam(required = false) Tier tier,
            @RequestParam(required = false) String hero
    ) {
        return itemService.getItems(tier, hero);
    }

    @GetMapping("/id/{id}")
    public ItemResponseDto getItemById(@PathVariable String id) {
        return itemService.getItemById(id);
    }
}