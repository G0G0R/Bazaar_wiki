package com.main.wiki.item.dto;

import com.main.common.util.Tier;

import java.util.List;

public class ItemResponseDto {

    private String id;
    private String name;
    private Tier tier;
    private List<String> heroes;
    private List<String> tags;
    private List<String> hiddenTags;

    public ItemResponseDto() {}

    public ItemResponseDto(
            String id,
            String name,
            Tier tier,
            List<String> heroes,
            List<String> tags,
            List<String> hiddenTags) {

        this.id = id;
        this.name = name;
        this.tier = tier;
        this.heroes = heroes;
        this.tags = tags;
        this.hiddenTags = hiddenTags;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public Tier getTier() { return tier; }
    public List<String> getHeroes() { return heroes; }
    public List<String> getTags() { return tags; }
    public List<String> getHiddenTags() { return hiddenTags; }

    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setTier(Tier tier) { this.tier = tier; }
    public void setHeroes(List<String> heroes) { this.heroes = heroes; }
    public void setTags(List<String> tags) { this.tags = tags; }
    public void setHiddenTags(List<String> hiddenTags) { this.hiddenTags = hiddenTags; }
}