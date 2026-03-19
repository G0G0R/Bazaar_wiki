package com.main.wiki.item.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TitleJson {

    @JsonProperty("Text")
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
