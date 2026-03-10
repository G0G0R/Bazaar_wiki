package com.main.wiki.model;

import com.main.common.util.Tier;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

import java.util.List;

@Entity
public class Item {

    @Id
    private String id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Tier tier;

    private List<String> heroes;

    private List<String> tags;

    private List<String> hiddenTags;

    public Item() {
    }

    public Item(String id, String name, Tier tier, List<String> heroes, List<String> tags, List<String> hiddenTags) {
        this.id = id;
        this.name = name;
        this.tier = tier;
        this.heroes = heroes;
        this.tags = tags;
        this.hiddenTags = hiddenTags;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Tier getTier() {
        return tier;
    }

    public void setTier(Tier tier) {
        this.tier = tier;
    }

    public List<String> getHeroes() {
        return heroes;
    }

    public void setHeroes(List<String> heroes) {
        this.heroes = heroes;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getHiddenTags() {
        return hiddenTags;
    }

    public void setHiddenTags(List<String> hiddenTags) {
        this.hiddenTags = hiddenTags;
    }


}