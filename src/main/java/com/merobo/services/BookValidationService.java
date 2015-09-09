package com.merobo.services;

import org.springframework.stereotype.Service;

import com.merobo.dtos.BookingTo;

@Service
public class BookValidationService implements ValidationService<BookingTo> {

	@Override
	public boolean validate(BookingTo bookingTo) {
		return false;
	}

}
