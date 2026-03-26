package com.main.wiki.item.model;

import jakarta.persistence.*;
import com.main.common.util.Size;
import com.main.common.util.Tier;

import java.util.List;

@Entity
public class Item {

    @Id
    private String id;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<Effect> effects;

    private String name;

    @Enumerated(EnumType.STRING)
    private Tier tier;

    @ElementCollection
    @CollectionTable(name = "item_heroes")
    @Column(name = "hero")
    private List<String> heroes;

    private List<String> tags;

    private List<String> hiddenTags;

    @Enumerated(EnumType.STRING)
    private Size size;

    public Item() {
    }

    public Item(String id, List<Effect> effects, String name, Tier tier, List<String> heroes, List<String> tags, List<String> hiddenTags) {
        this.id = id;
        this.effects = effects;
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

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public List<Effect> getEffects() {
        return effects;
    }

    public void setEffects(List<Effect> effects) {
        this.effects = effects;
    }
}