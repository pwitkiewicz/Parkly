package com.parkly.backend.utils;

import lombok.experimental.UtilityClass;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;

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

    public static Duration timestampsToDuration(final Long startTime, final Long endTime)
    {
        final Instant startInstant = Instant.ofEpochSecond(startTime);
        final Instant endInstant = Instant.ofEpochSecond(endTime);
        return Duration.between(startInstant, endInstant);
    }

    public static Long stringToUnixTimestamp(final String dateTimeString) {
        final OffsetDateTime dateTime = OffsetDateTime.parse(dateTimeString, formatter);
        return dateTime.atZoneSameInstant(ZoneOffset.UTC).toInstant().getEpochSecond();
    }

    public static String unixTimestampToString(final Long timestamp) {
        final OffsetDateTime dateTime = OffsetDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZoneOffset.UTC);

        return dateTime.format(formatter).toString();
    }
}
