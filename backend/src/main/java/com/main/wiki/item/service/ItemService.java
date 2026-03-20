package com.main.wiki.item.service;

import com.main.common.util.Tier;
import com.main.wiki.item.dto.ItemResponseDto;
import com.main.wiki.item.mapper.ItemResponseMapper;
import com.main.wiki.item.model.Item;
import com.main.wiki.item.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemResponseMapper mapper;

    public ItemService(ItemRepository itemRepository,
                       ItemResponseMapper mapper) {
        this.itemRepository = itemRepository;
        this.mapper = mapper;
    }

    public List<ItemResponseDto> getAllItems() {
        return itemRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    public ItemResponseDto getItemById(String id) {
        return itemRepository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() ->
                        new RuntimeException("ItemService not found: " + id));
    }

    public List<ItemResponseDto> getItems(Tier tier, String hero) {

        List<Item> items;

        if (tier != null && hero != null) {
            items = itemRepository.findByTierAndHero(tier, hero);

        } else if (tier != null) {
            items = itemRepository.findByTier(tier);

        } else if (hero != null) {
            items = itemRepository.findByHero(hero);

        } else {
            items = itemRepository.findAll();
        }

        return items.stream()
                .map(mapper::toDto)
                .toList();
    }
}