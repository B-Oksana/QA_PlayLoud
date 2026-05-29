package com.playLoad.dto.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ErrorListDto(@JsonIgnoreProperties("message")List<String> message,
                           @JsonProperty("error") String error) {

    public  static ErrorListDto of(List<String> message, String error) {
        return new ErrorListDto(message, error);
    }
}
