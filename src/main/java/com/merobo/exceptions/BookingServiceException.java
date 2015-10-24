package com.merobo.exceptions;

public class BookingServiceException extends ServiceException {

	private static final long serialVersionUID = -2880900488413152495L;

	public BookingServiceException() {
	}

	public BookingServiceException(String message) {
		super(message);
	}

	public BookingServiceException(Throwable cause) {
		super(cause);
	}

	public BookingServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public BookingServiceException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
