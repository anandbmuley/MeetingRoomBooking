package com.merobo.dataproviders;

import java.util.ArrayList;
import java.util.List;

import com.merobo.dtos.BookingTo;

public class BookingTestDataProvider {

	public static BookingTo bookRoom() {
		BookingTo bookingTo = new BookingTo();
		bookingTo.setBookedBy("Alia Khan");
		bookingTo.setBookedWhen("11-Sep-2015");
		bookingTo.setEndTime("10:00 am");
		bookingTo.setStartTime("09:00 am");
		bookingTo.setTeamName("Rockers");
		return bookingTo;
	}

	public static List<BookingTo> getAll() {
		List<BookingTo> bookingTos = new ArrayList<BookingTo>();
		for (int i = 0; i < 2; i++) {
			int st = 9;
			BookingTo bookingTo = new BookingTo();
			bookingTo.setId("BID10" + i);
			bookingTo.setBookedBy("User" + i);
			bookingTo.setBookedWhen("11-Sep-2015");
			bookingTo.setEndTime("" + (st + i + 1) + ":00 am");
			bookingTo.setStartTime("" + (st + i) + ":00 am");
			bookingTo.setTeamName("Team" + i);
			bookingTos.add(bookingTo);
		}
		return bookingTos;
	}

}
