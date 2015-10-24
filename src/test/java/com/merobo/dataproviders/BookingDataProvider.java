package com.merobo.dataproviders;

import java.text.ParseException;

import org.testng.annotations.DataProvider;

import com.merobo.dtos.BookingTo;
import com.merobo.utils.MeetingRooms;

public class BookingDataProvider {

	@DataProvider(name = "BookingValidation")
	public static Object[][] bookingValidation() {
		try {
			String bookedWhen = "08:00 AM";
			// Data 1 : Start Time is clashing
			BookingTo bookingTo = new BookingTo();
			bookingTo.setBookedBy("Akira Khan");
			bookingTo.setBookedWhen(bookedWhen);
			bookingTo.setStartTime("02:15 PM");
			bookingTo.setEndTime("02:45 PM");
			bookingTo.setRoomName(MeetingRooms.PINNACLE.name());
			bookingTo.setTeamName("KANBAN");

			// Data 2 : End Time is clashing
			BookingTo bookingToET = new BookingTo();
			bookingToET.setBookedBy("Akira Khan");
			bookingToET.setBookedWhen(bookedWhen);
			bookingToET.setStartTime("01:45 PM");
			bookingToET.setEndTime("02:15 PM");
			bookingToET.setRoomName(MeetingRooms.PINNACLE.name());
			bookingToET.setTeamName("KANBAN");

			// Data 3 : Start Time Equals
			BookingTo bookingToSTE = new BookingTo();
			bookingToSTE.setBookedBy("Akira Khan");
			bookingToSTE.setBookedWhen(bookedWhen);
			bookingToSTE.setStartTime("02:00 PM");
			bookingToSTE.setEndTime("05:30 PM");
			bookingToSTE.setRoomName(MeetingRooms.PINNACLE.name());
			bookingToSTE.setTeamName("KANBAN");

			return new Object[][] { { bookingTo }, { bookingToET },
					{ bookingToSTE } };
		} catch (ParseException parseException) {
			parseException.printStackTrace();
		}
		return new Object[][] { {} };

	}

}
