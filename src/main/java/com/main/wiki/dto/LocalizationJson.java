package com.main.wiki.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LocalizationJson {

    @JsonProperty("Title")
    private TitleJson title;

    public TitleJson getTitle() {
        return title;
    }

    public void setTitle(TitleJson title) {
        this.title = title;
    }
}
