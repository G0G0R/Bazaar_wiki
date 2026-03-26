package com.main.wiki.item.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContentJson {

    @JsonProperty("Key")
    private String key;

    @JsonProperty("Text")
    private String text;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
