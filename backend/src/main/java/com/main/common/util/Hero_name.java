package com.main.common.util;

public enum Hero_name {

    PYGMALIEN,
    VANESSA,
    DOOLEY,
    MAK,
    STELLE,
    JULES,
    KARNOK,
    COMMON;

    public static Hero_name fromValue(String value) {
        if (value == null) {
            return null;
        }

        try {
            return Hero_name.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException _) {
            throw new IllegalArgumentException("Unknown hero name: " + value);
        }
    }

}