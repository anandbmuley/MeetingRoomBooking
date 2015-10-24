package com.merobo.exceptions.booking;

public class StartTimeClashesException extends BookingClashesException {

	private static final long serialVersionUID = -3690072856428679140L;

	public StartTimeClashesException() {
	}

	public StartTimeClashesException(String message) {
		super(message);
	}

	public StartTimeClashesException(Throwable cause) {
		super(cause);
	}

	public StartTimeClashesException(String message, Throwable cause) {
		super(message, cause);
	}

	public StartTimeClashesException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
