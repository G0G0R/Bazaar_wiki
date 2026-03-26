package com.main.wiki.item.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AttributeJson {

    @JsonProperty("CooldownMax")
    private Integer cooldownMax;

    public Integer getCooldownMax() {
        return cooldownMax;
    }

    public void setCooldownMax(Integer cooldownMax) {
        this.cooldownMax = cooldownMax;
    }
}
