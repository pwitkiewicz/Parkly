package com.parkly.backend.utils;

import lombok.experimental.UtilityClass;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@UtilityClass
public class TimeUtils {
    public static Duration timestampsToDuration(final Long startTime, final Long endTime) {
        final Instant startInstant = Instant.ofEpochSecond(startTime);
        final Instant endInstant = Instant.ofEpochSecond(endTime);
        return Duration.between(startInstant, endInstant);
    }

    public static Long stringToUnixTimestamp(final String dateTimeString) {
        if (Objects.isNull(dateTimeString)) {
            return null;
        }

        final OffsetDateTime dateTime = OffsetDateTime.parse(dateTimeString, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        return dateTime.atZoneSameInstant(ZoneOffset.UTC).toInstant().getEpochSecond();
    }

    public static String unixTimestampToString(final Long timestamp) {
        if (Objects.isNull(timestamp)) {
            return null;
        }
        final ZonedDateTime dateTime = ZonedDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZoneOffset.UTC);
        return dateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }
}
