package com.main.common.util;

public enum HeroName {

    PYGMALIEN,
    VANESSA,
    DOOLEY,
    MAK,
    STELLE,
    JULES,
    KARNOK,
    COMMON;

    public static HeroName fromValue(String value) {
        if (value == null) {
            return null;
        }

        try {
            return HeroName.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException _) {
            throw new IllegalArgumentException("Unknown hero name: " + value);
        }
    }

}