package com.main.wiki.dto;

public class ItemJson {

    public String Id;

    public String StartingTier;

    public LocalizationJson Localization;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getStartingTier() {
        return StartingTier;
    }

    public void setStartingTier(String startingTier) {
        StartingTier = startingTier;
    }

    public LocalizationJson getLocalization() {
        return Localization;
    }

    public void setLocalization(LocalizationJson localization) {
        Localization = localization;
    }
}
