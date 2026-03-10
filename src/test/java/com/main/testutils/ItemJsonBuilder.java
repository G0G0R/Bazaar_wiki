package com.main.testutils;

import com.main.wiki.dto.ItemJson;
import com.main.wiki.dto.LocalizationJson;
import com.main.wiki.dto.TitleJson;

import java.util.List;

public class ItemJsonBuilder {

    private final ItemJson item = new ItemJson();

    public static ItemJsonBuilder anItem() {
        return new ItemJsonBuilder();
    }

    public ItemJsonBuilder withId(String id) {
        item.setId(id);
        return this;
    }

    public ItemJsonBuilder withTier(String tier) {
        item.setStartingTier(tier);
        return this;
    }

    public ItemJsonBuilder withName(String name) {

        TitleJson title = new TitleJson();
        title.setText(name);

        LocalizationJson loc = new LocalizationJson();
        loc.setTitle(title);

        item.setLocalization(loc);

        return this;
    }

    public ItemJsonBuilder withHeroes(String... heroes) {
        item.setHeroes(List.of(heroes));
        return this;
    }

    public ItemJsonBuilder withTags(String... tags) {
        item.setTags(List.of(tags));
        return this;
    }

    public ItemJsonBuilder withHiddenTags(String... tags) {
        item.setHiddenTags(List.of(tags));
        return this;
    }

    public ItemJson build() {
        return item;
    }
}