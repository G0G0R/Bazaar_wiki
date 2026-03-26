package com.main.wiki.item.mapper;

import com.main.wiki.item.dto.ItemResponseDto;
import com.main.wiki.item.model.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemResponseMapper {

    public ItemResponseDto toDto(Item item) {

        if (item == null) {
            return null;
        }

        return new ItemResponseDto(
                item.getId(),
                item.getName(),
                item.getTier(),
                item.getHeroes(),
                item.getTags(),
                item.getHiddenTags(),
                item.getSize()
        );
    }
}