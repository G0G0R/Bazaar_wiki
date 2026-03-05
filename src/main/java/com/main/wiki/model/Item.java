package com.main.wiki.model;

import com.main.common.util.Tier;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

@Entity
public class Item {

    @Id
    private String id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Tier tier;

    public Item() {
    }

    public Item(String id, String name, Tier tier) {
        this.id = id;
        this.name = name;
        this.tier = tier;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Tier getTier() {
        return tier;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTier(Tier tier) {
        this.tier = tier;
    }
}