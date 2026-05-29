package com.playLoad.dto.users;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UsersRequestDto(@JsonProperty("name") String name,
                              @JsonProperty("email") String email,
                              @JsonProperty("password") String password) {


    public static UsersRequestDto of(String name, String email, String password) {
        return  new UsersRequestDto(name, email, password);
    }
}
