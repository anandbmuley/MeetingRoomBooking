package com.merobo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.merobo.beans.Booking;
import com.merobo.utils.BookingStatus;

@Component
public class BookingFilter {

	public List<Booking> filterCancelled(List<Booking> bookings) {
		List<Booking> filtered = new ArrayList<Booking>();
		for (Booking booking : bookings) {
			if (BookingStatus.BOOKED.equals(booking.getStatus())) {
				filtered.add(booking);
			}
		}
		return filtered;
	}

}
