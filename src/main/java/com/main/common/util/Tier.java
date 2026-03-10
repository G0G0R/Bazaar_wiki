package com.main.common.util;
public enum Tier {

    BRONZE,
    SILVER,
    GOLD,
    DIAMOND,
    LEGENDARY;

    public static Tier fromValue(String value) {
        if (value == null) {
            return null;
        }

        try {
            return Tier.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException _) {
            throw new IllegalArgumentException("Unknown tier value: " + value);
        }
    }

}