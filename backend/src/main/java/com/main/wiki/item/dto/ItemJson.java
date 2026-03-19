package com.main.wiki.item.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ItemJson {

    @JsonProperty("Id")
    private String id;

    @JsonProperty("StartingTier")
    private String startingTier;

    @JsonProperty("Localization")
    private LocalizationJson localization;

    private List<String> heroes;

    private List<String> tags;

    private List<String> hiddenTags;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartingTier() {
        return startingTier;
    }

    public void setStartingTier(String startingTier) {
        this.startingTier = startingTier;
    }

    public LocalizationJson getLocalization() {
        return localization;
    }

    public void setLocalization(LocalizationJson localization) {
        this.localization = localization;
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
