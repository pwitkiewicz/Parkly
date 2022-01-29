package com.parkly.backend.utils;

import static java.util.stream.Collectors.joining;

import com.parkly.backend.utils.domain.LogTypeEnum;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;

@Slf4j
@UtilityClass
public class LogWriter {
    public static void logException(Exception e)
    {
        log.warn("Exception thrown: ", e);
    }

    public static void logHeaders(@RequestHeader HttpHeaders headers)
    {
        log.debug("Controller request headers {}",
            headers.entrySet()
                .stream()
                .map(entry -> String.format("%s->[%s]", entry.getKey(), String.join(",", entry.getValue())))
                .collect(joining(","))
        );
    }
}
