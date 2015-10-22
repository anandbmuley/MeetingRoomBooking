package com.merobo.services;

import com.merobo.dtos.BookingTo;
import com.merobo.dtos.MeetingRoomTo;

public interface BookingService {

	BookingTo bookRoom(BookingTo bookingTo);

	MeetingRoomTo getAll();

}
