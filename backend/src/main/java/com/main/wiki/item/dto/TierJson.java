package com.main.wiki.item.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TierJson {

    @JsonProperty("Attributes")
    private AttributeJson attributes;

    public AttributeJson getAttributes() {
        return attributes;
    }

    public void setAttributes(AttributeJson attributes) {
        this.attributes = attributes;
    }
}
