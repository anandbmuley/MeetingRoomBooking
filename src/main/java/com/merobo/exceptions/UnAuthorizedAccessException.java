package com.merobo.exceptions;

public class UnAuthorizedAccessException extends MeroboException {

    public UnAuthorizedAccessException() {
    }

    public UnAuthorizedAccessException(String message) {
        super(message);
    }
}
