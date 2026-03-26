package com.main.wiki.item.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum EffectType {
    ACTIVE,
    PASSIVE;

    @JsonCreator
    public static EffectType fromValue(String value) {
        if (value == null) {
            return null;
        }

        try {
            return EffectType.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException _) {
            throw new IllegalArgumentException("Unknown effect type value: " + value);
        }
    }
}
