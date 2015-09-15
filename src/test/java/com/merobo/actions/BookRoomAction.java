package com.merobo.actions;

import org.hamcrest.Description;
import org.jmock.api.Action;
import org.jmock.api.Invocation;

import com.merobo.dtos.BookingTo;

public class BookRoomAction implements Action {

	@Override
	public void describeTo(Description description) {
		description.appendText("Booking Room...");
	}

	@Override
	public Object invoke(Invocation invocation) throws Throwable {
		BookingTo bookingTo = (BookingTo) invocation.getParameter(0);
		bookingTo.setId("BID101");
		return bookingTo;
	}

}
