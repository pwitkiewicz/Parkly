package com.parkly.backend.rest.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;


@Data(staticConstructor = "of")
public class PhotoRest implements Serializable {

    public static final PhotoRest EMPTY_PHOTO = PhotoRest.of(null, null);

    @JsonProperty("id")
    private final Long photoId;

    @JsonProperty("path")
    private final String path;
}
