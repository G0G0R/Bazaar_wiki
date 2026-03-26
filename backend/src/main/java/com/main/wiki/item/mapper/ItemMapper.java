package com.main.wiki.item.mapper;

import com.main.common.util.Size;
import com.main.common.util.Tier;
import com.main.wiki.item.dto.ItemJson;
import com.main.wiki.item.dto.TierJson;
import com.main.wiki.item.dto.TooltipsJson;
import com.main.wiki.item.model.Effect;
import com.main.wiki.item.model.EffectType;
import com.main.wiki.item.model.Item;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ItemMapper {

    public Item dtoToItem(ItemJson dto) {

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
        item.setSize(Size.fromValue(dto.getSize()));

        return item;
    }

    public List<Effect> dtoToEffect(ItemJson dto, Item item) {
        List<Effect> effectList = new ArrayList<>();
        Effect e;

        for (TooltipsJson tt : dto.getLocalization().getTooltipsList()) {
            e = new Effect();
            e.setItem(item);
            e.setEffectType(tt.getType());
            if(tt.getType().equals(EffectType.ACTIVE)){
                List<Integer> cd;
                cd = new ArrayList<>();
                for(TierJson tier : dto.getTiers().values()) {
                    if(null != tier.getAttributes().getCooldownMax()){
                        cd.add(tier.getAttributes().getCooldownMax());
                    }
                }
                e.setCooldowns(cd);
            }
            e.setText(tt.getContent().getText());
            effectList.add(e);
        }

        return effectList;
    }
}