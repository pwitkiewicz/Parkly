package com.parkly.backend.rest.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

public class PhotoRest implements Serializable {

    @JsonProperty("id")
    private long photoId;

    @JsonProperty("path")
    private String path;

    public long getPhotoId() { return photoId; }

    public void setPhotoId(long photoId) { this.photoId = photoId; }

    public String getPath() { return path; }

    public void setPath(String path) { this.path = path; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhotoRest photoRest = (PhotoRest) o;
        return photoId == photoRest.photoId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(photoId);
    }
}
