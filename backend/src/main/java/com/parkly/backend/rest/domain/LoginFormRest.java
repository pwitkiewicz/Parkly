package com.parkly.backend.rest.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginFormRest {

    @JsonProperty("login")
    private String login;
    @JsonProperty("password")
    private String password;
}