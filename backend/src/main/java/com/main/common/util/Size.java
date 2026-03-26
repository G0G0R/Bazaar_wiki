package com.main.common.util;

public enum Size {
    SMALL,
    MEDIUM,
    LARGE;

    public static Size fromValue(String value) {
        if (value == null) {
            return null;
        }

        try {
            return Size.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException _) {
            throw new IllegalArgumentException("Unknown size value: " + value);
        }
    }
}