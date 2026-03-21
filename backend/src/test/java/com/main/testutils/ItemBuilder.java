package com.main.testutils;

import com.main.common.util.HeroName;
import com.main.common.util.Tier;
import com.main.wiki.item.dto.ItemResponseDto;
import com.main.wiki.item.model.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemBuilder {

    public static List<ItemResponseDto> getListItemsDTO() {
        List<ItemResponseDto> res = new ArrayList<>();

        List<String> listHero = new ArrayList<>();

        listHero.add(HeroName.DOOLEY.name());

        res.add(new ItemResponseDto("1", "itemDTO 1", Tier.BRONZE, listHero, new ArrayList<>(), new ArrayList<>()));

        listHero.clear();
        listHero.add(HeroName.VANESSA.name());

        res.add(new ItemResponseDto("2", "itemDTO 2", Tier.SILVER, listHero, new ArrayList<>(), new ArrayList<>()));
        res.add(new ItemResponseDto("3", "itemDTO 3", Tier.DIAMOND, listHero, new ArrayList<>(), new ArrayList<>()));

        return res;
    }

    public static List<Item> getListItem() {
        List<Item> res = new ArrayList<>();

        List<String> listHero = new ArrayList<>();

        listHero.add(HeroName.DOOLEY.name());

        res.add(new Item("1", "item 1", Tier.BRONZE, listHero, new ArrayList<>(), new ArrayList<>()));

        listHero.clear();
        listHero.add(HeroName.VANESSA.name());

        res.add(new Item("2", "item 2", Tier.SILVER, listHero, new ArrayList<>(), new ArrayList<>()));
        res.add(new Item("3", "item 3", Tier.DIAMOND, listHero, new ArrayList<>(), new ArrayList<>()));

        return res;
    }

    public static Item getItem() {
        List<String> listHero = new ArrayList<>();

        listHero.add(HeroName.DOOLEY.name());

        return new Item("1", "item 1", Tier.BRONZE, listHero, new ArrayList<>(), new ArrayList<>());

    }

    public static ItemResponseDto getItemDTO() {
        List<String> listHero = new ArrayList<>();

        listHero.add(HeroName.DOOLEY.name());

        return new ItemResponseDto("1", "item 1", Tier.BRONZE, listHero, new ArrayList<>(), new ArrayList<>());

    }

}
