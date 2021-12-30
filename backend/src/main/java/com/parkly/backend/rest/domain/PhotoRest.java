package com.parkly.backend.rest.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PhotoRest implements Serializable {

    @EqualsAndHashCode.Include
    @JsonProperty("id")
    private long photoId;

    @JsonProperty("path")
    private String path;
}
