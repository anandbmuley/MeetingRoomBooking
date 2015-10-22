package com.merobo.exceptions;

public class DuplicateUsernameException extends UserServiceException {

	private static final long serialVersionUID = -89589866227701409L;

	public DuplicateUsernameException(String message) {
		super(message);
	}

}
