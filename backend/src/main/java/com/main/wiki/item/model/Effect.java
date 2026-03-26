package com.main.wiki.item.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Effect {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Enumerated(EnumType.STRING)
    private EffectType effectType;

    private List<Integer> cooldowns;

    private String text;

    public Effect() {}

    public Effect(Item item, EffectType effectType, List<Integer> cooldowns, String text) {
        this.item = item;
        this.effectType = effectType;
        this.cooldowns = cooldowns;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public EffectType getEffectType() {
        return effectType;
    }

    public void setEffectType(EffectType effType) {
        this.effectType = effType;
    }

    public List<Integer> getCooldowns() {
        return cooldowns;
    }

    public void setCooldowns(List<Integer> cooldowns) {
        this.cooldowns = cooldowns;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
