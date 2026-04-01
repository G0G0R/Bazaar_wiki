package com.main.wiki.item.dto;

import com.main.common.util.Size;
import com.main.common.util.Tier;

import java.util.List;
import java.util.Objects;

public class ItemResponseDto {

    private String id;
    private String name;
    private Tier tier;
    private List<String> heroes;
    private List<String> tags;
    private List<String> hiddenTags;
    private Size size;
    private List<EffectResponseDto> effects;

    public ItemResponseDto() {
    }

    public ItemResponseDto(
            String id,
            String name,
            Tier tier,
            List<String> heroes,
            List<String> tags,
            List<String> hiddenTags,
            Size size,
            List<EffectResponseDto> effects) {

        this.id = id;
        this.name = name;
        this.tier = tier;
        this.heroes = heroes;
        this.tags = tags;
        this.hiddenTags = hiddenTags;
        this.size = size;
        this.effects = effects;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Tier getTier() {
        return tier;
    }

    public List<String> getHeroes() {
        return heroes;
    }

    public List<String> getTags() {
        return tags;
    }

    public List<String> getHiddenTags() {
        return hiddenTags;
    }

    public Size getSize() {
        return size;
    }

    public List<EffectResponseDto> getEffects() {
        return effects;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTier(Tier tier) {
        this.tier = tier;
    }

    public void setHeroes(List<String> heroes) {
        this.heroes = heroes;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void setHiddenTags(List<String> hiddenTags) {
        this.hiddenTags = hiddenTags;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public void setEffects(List<EffectResponseDto> effects) {
        this.effects = effects;
    }

    @Override
    public boolean equals(Object compared) {
        if (compared == null || getClass() != compared.getClass()) {
            return false;
        }

        return Objects.equals(id, ((ItemResponseDto) compared).getId());
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}