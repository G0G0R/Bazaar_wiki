package com.main.wiki.item.mapper;

import com.main.common.util.Tier;
import com.main.wiki.item.dto.ItemJson;
import com.main.wiki.item.model.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    public Item toEntity(ItemJson dto) {

        if (dto == null
                || dto.getLocalization() == null
                || dto.getLocalization().getTitle() == null) {
            return null;
        }

        Item item = new Item();

        item.setId(dto.getId());
        item.setName(dto.getLocalization().getTitle().getText());
        item.setTier(Tier.fromValue(dto.getStartingTier()));
        item.setHeroes(dto.getHeroes());
        item.setTags(dto.getTags());
        item.setHiddenTags(dto.getHiddenTags());

        return item;
    }
}