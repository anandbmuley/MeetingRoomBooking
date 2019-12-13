package com.merobo.exceptions;

public class RoomServiceException extends ServiceException {

    public RoomServiceException() {
    }

    public RoomServiceException(String message) {
        super(message);
    }

    public RoomServiceException(Throwable cause) {
        super(cause);
    }
}
