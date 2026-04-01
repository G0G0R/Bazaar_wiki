package com.main.wiki.item.dto;

import com.main.wiki.item.model.EffectType;

import java.util.List;

public class EffectResponseDto {

    private EffectType effectType;
    private List<Integer> cooldowns;
    private String text;

    public EffectResponseDto() {
    }

    public EffectResponseDto(EffectType effectType, List<Integer> cooldowns, String text) {
        this.effectType = effectType;
        this.cooldowns = cooldowns;
        this.text = text;
    }

    public EffectType getEffectType() {
        return effectType;
    }

    public List<Integer> getCooldowns() {
        return cooldowns;
    }

    public String getText() {
        return text;
    }

    public void setEffectType(EffectType effectType) {
        this.effectType = effectType;
    }

    public void setCooldowns(List<Integer> cooldowns) {
        this.cooldowns = cooldowns;
    }

    public void setText(String text) {
        this.text = text;
    }
}