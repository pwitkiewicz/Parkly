package com.parkly.backend.utils;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TimeUtilsTest {

    @Test
    void convertCorrectlyStringToUnixTimestamp() {
        var localDateTimeString = "Sun Jan 30 2022 15:12:45 GMT+0100 (CET)";
        long timestamp = TimeUtils.stringToUnixTimestamp(localDateTimeString);

        assertThat(timestamp).isEqualTo(1643551965L);
    }

    @Test
    void convertCorrectlyTimestampToString() {
        var timestamp = 1643551965L;
        var localDateTimeString = TimeUtils.unixTimestampToString(timestamp);

        assertThat(localDateTimeString).isEqualTo("Sun Jan 30 2022 14:12:45 GMT+0000");
    }
}