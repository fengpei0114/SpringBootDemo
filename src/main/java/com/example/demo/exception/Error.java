package com.example.demo.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Error {

    private final String message;

    public static Error of(String message) {
        return Error.builder().message(message).build();
    }

}
