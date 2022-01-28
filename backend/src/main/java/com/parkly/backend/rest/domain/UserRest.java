package com.parkly.backend.rest.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserRest {

    @NonNull
    @JsonProperty("id")
    @EqualsAndHashCode.Include
    private Long userId;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @NonNull
    @JsonProperty("login")
    private String login;

    @NonNull
    @JsonProperty("password")
    private String password;

    @NonNull
    @JsonProperty("securityToken")
    private String securityToken;
}
