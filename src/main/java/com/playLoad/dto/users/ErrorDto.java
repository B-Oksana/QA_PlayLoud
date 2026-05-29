package com.playLoad.dto.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ErrorDto(@JsonProperty("message") String message){

    public static ErrorDto of(String message){
        return new ErrorDto(message);
    }
}
