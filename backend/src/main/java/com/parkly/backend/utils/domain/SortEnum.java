package com.parkly.backend.utils.domain;

public enum SortEnum {
    NONE(0),
    ASCENDING(1),
    DESCENDING(-1);

    private Integer value;

    SortEnum(final Integer value)
    {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
