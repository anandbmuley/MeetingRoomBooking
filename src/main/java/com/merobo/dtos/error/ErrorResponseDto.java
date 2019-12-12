package com.merobo.dtos.error;

public class ErrorResponseDto {

    private final String message;

    public ErrorResponseDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
