package com.parkly.backend.utils;

import lombok.experimental.UtilityClass;

import java.time.Duration;
import java.time.Instant;

@UtilityClass
public class TimeUtils {
    public static Duration timestampsToDuration(Long startTime, Long endTime) {
        var startInstant = Instant.ofEpochMilli(startTime);
        var endInstant = Instant.ofEpochMilli(endTime);
        return Duration.between(startInstant, endInstant);
    }
}
