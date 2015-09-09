package com.merobo.services;

import java.util.List;

import com.merobo.dtos.BookingTo;

public interface BookingService {

	BookingTo bookRoom(BookingTo bookingTo);

	List<BookingTo> getAll();

}
