package com.parkly.backend.utils.domain;

public enum FilterEnum {
    ALL(-1),
    ACTIVE(1),
    INACTIVE(0);

    private final int value;

    FilterEnum(final int value)
    {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
