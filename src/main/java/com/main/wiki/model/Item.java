package com.main.wiki.model;

import com.main.common.util.Tier;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
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

}