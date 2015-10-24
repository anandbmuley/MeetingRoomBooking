package com.merobo.exceptions.booking;

public class EndTimeClashesException extends BookingClashesException {

	private static final long serialVersionUID = -5144079222579378135L;

	public EndTimeClashesException() {
	}

	public EndTimeClashesException(String message) {
		super(message);
	}

	public EndTimeClashesException(Throwable cause) {
		super(cause);
	}

	public EndTimeClashesException(String message, Throwable cause) {
		super(message, cause);
	}

	public EndTimeClashesException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
