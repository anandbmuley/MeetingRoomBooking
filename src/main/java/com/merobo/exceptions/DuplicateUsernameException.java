package com.merobo.exceptions;

public class DuplicateUsernameException extends UserServiceException {

    private static final long serialVersionUID = -89589866227701409L;
    private static String defaultMessage = "Username is already taken";

    public DuplicateUsernameException() {
        super(defaultMessage);
    }

    public DuplicateUsernameException(String message) {
        super(message);
    }

}
