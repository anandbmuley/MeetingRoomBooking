package com.merobo.exceptions;

public class UserNotFoundException extends UserServiceException {

	private static final long serialVersionUID = -8752839976151339944L;

	public UserNotFoundException(){
		super("User not found");
	}

	public UserNotFoundException(String message) {
		super(message);
	}

}
