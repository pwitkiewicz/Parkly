package com.parkly.backend.utils;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TimeUtilsTest {

    @Test
    void convertCorrectlyStringToUnixTimestamp() {
        var localDateTimeString = "2022-02-10T18:45:04.000Z";
        long timestamp = TimeUtils.stringToUnixTimestamp(localDateTimeString);

        assertThat(timestamp).isEqualTo(1644518704L);
    }

    @Test
    void convertCorrectlyTimestampToString() {
        var timestamp = 1644518704L;
        var localDateTimeString = TimeUtils.unixTimestampToString(timestamp);

        assertThat(localDateTimeString).isEqualTo("2022-02-10T18:45:04Z");
    }
}