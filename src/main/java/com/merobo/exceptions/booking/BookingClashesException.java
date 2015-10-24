package com.merobo.exceptions.booking;

import com.merobo.exceptions.BookingValidationServiceException;

public class BookingClashesException extends BookingValidationServiceException {

	private static final long serialVersionUID = 5077524510450197557L;

	public BookingClashesException() {
	}

	public BookingClashesException(String message) {
		super(message);
	}

	public BookingClashesException(Throwable cause) {
		super(cause);
	}

	public BookingClashesException(String message, Throwable cause) {
		super(message, cause);
	}

	public BookingClashesException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
