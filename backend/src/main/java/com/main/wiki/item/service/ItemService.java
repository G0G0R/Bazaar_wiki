package com.main.wiki.item.service;

import com.main.common.util.Size;
import com.main.common.util.Tier;
import com.main.wiki.item.dto.ItemResponseDto;
import com.main.wiki.item.mapper.ItemResponseMapper;
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
                        new RuntimeException("Item not found: " + id));
    }

    public List<ItemResponseDto> getItemByTier(Tier tier) {
        return itemRepository.findByTier(tier).
                stream()
                .map(mapper::toDto)
                .toList();
    }

    public List<ItemResponseDto> getItemByHero(String hero) {
        return itemRepository.findByHero(hero)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    public List<ItemResponseDto> getItemBySize(Size size) {
        return itemRepository.findBySize(size)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    public List<ItemResponseDto> getItemByTags(List<String> tags) {
        return itemRepository.findByTags(tags)
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}