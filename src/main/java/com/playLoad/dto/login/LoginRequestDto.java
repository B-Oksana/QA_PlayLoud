package com.playLoad.dto.login;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LoginRequestDto(@JsonProperty("email") String email,
                              @JsonProperty("password") String password) {

    public static LoginRequestDto of(String email, String password) {
        return new LoginRequestDto(email, password);
    }
}
