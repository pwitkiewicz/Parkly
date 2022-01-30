package com.parkly.backend.utils;

import lombok.experimental.UtilityClass;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Objects;

@UtilityClass
public class TimeUtils {
    private static final DateTimeFormatter formatter = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendPattern("E MMM d uuuu H:m:s")
            .appendLiteral(" ")
            .appendZoneId()
            .appendPattern("X")
            .appendLiteral(" ")
            .appendLiteral("(")
            .appendGenericZoneText(TextStyle.FULL)
            .appendLiteral(')')
            .toFormatter(Locale.ENGLISH);

    private static final DateTimeFormatter formatter2 = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendPattern("E MMM d uuuu HH:mm:ss 'GMT'Z")
            .toFormatter(Locale.ENGLISH);

    public static Duration timestampsToDuration(final Long startTime, final Long endTime)
    {
        final Instant startInstant = Instant.ofEpochSecond(startTime);
        final Instant endInstant = Instant.ofEpochSecond(endTime);
        return Duration.between(startInstant, endInstant);
    }

    public static Long stringToUnixTimestamp(final String dateTimeString) {
        if(Objects.isNull(dateTimeString)) {
            return null;
        }
        try {
            final OffsetDateTime dateTime = OffsetDateTime.parse(dateTimeString, formatter);
            return dateTime.atZoneSameInstant(ZoneOffset.UTC).toInstant().getEpochSecond();
        } catch (DateTimeParseException e) {
            final OffsetDateTime dateTime = OffsetDateTime.parse(dateTimeString, formatter2);
            return dateTime.atZoneSameInstant(ZoneOffset.UTC).toInstant().getEpochSecond();
        }
    }

    public static String unixTimestampToString(final Long timestamp) {
        if(Objects.isNull(timestamp)) {
            return null;
        }
        final ZonedDateTime dateTime = ZonedDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZoneOffset.UTC);
        return dateTime.format(formatter2);
    }
}
