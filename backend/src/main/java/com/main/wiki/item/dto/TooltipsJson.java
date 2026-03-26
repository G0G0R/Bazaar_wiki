package com.main.wiki.item.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.main.wiki.item.model.EffectType;

public class TooltipsJson {

    @JsonProperty("Content")
    private ContentJson content;

    @JsonProperty("TooltipType")
    private EffectType type;

    public ContentJson getContent() {
        return content;
    }

    public void setContent(ContentJson content) {
        this.content = content;
    }

    public EffectType getType() {
        return type;
    }

    public void setType(EffectType type) {
        this.type = type;
    }
}
