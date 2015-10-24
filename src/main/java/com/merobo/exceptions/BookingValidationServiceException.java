package com.merobo.exceptions;


public class BookingValidationServiceException extends ServiceException {

	private static final long serialVersionUID = 4191426656746789694L;

	public BookingValidationServiceException() {
	}

	public BookingValidationServiceException(String message) {
		super(message);
	}

	public BookingValidationServiceException(Throwable cause) {
		super(cause);
	}

	public BookingValidationServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public BookingValidationServiceException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
