package com.merobo.services;

import java.text.ParseException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.merobo.beans.BookingBean;
import com.merobo.dataproviders.BookingDataProvider;
import com.merobo.dtos.BookingTo;
import com.merobo.exceptions.BookingValidationServiceException;
import com.merobo.exceptions.booking.BookingClashesException;
import com.merobo.utils.BookingStatus;
import com.merobo.utils.DateConverterUtil;
import com.merobo.utils.MeetingRooms;

public class BookingValidationServiceTest {

	private BookingValidationService bookingValidationService;

	@BeforeClass
	public void setUp() {
		bookingValidationService = new BookingValidationService();
	}

	@Test(expectedExceptions = BookingClashesException.class, dataProviderClass = BookingDataProvider.class, dataProvider = "BookingValidation")
	public void ShouldClashBookings(BookingTo bookingTo)
			throws BookingValidationServiceException, ParseException {
		// GIVEN
		String bookedWhen = "08:00 AM";
		BookingBean bookingBean = new BookingBean();
		bookingBean.setBookedBy("Aron Johnson");
		bookingBean.setBookedWhen(bookedWhen);
		bookingBean.setStartTime(DateConverterUtil.toDate("02:00 PM",
				DateConverterUtil.PATTERN_HH_MM_MERIDIAN));
		bookingBean.setEndTime(DateConverterUtil.toDate("03:30 PM",
				DateConverterUtil.PATTERN_HH_MM_MERIDIAN));
		bookingBean.setId("562ba47c44ae605f13522a83");
		bookingBean.setRoomName(MeetingRooms.PINNACLE.name());
		bookingBean.setStatus(BookingStatus.BOOKED);
		bookingBean.setTeamName("CVoS");

		// WHEN
		bookingValidationService.checkForClash(bookingTo, bookingBean);

		// THEN
	}

	@Test
	public void ShouldNotClashBooking()
			throws BookingValidationServiceException, ParseException {
		// GIVEN
		String bookedWhen = "08:00 AM";
		// Data 1 : Start Time is clashing
		BookingTo bookingTo = new BookingTo();
		bookingTo.setBookedBy("Akira Khan");
		bookingTo.setBookedWhen(bookedWhen);
		bookingTo.setStartTime("04:00 PM");
		bookingTo.setEndTime("05:00 PM");
		bookingTo.setRoomName(MeetingRooms.PINNACLE.name());
		bookingTo.setTeamName("KANBAN");

		BookingBean bookingBean = new BookingBean();
		bookingBean.setBookedBy("Aron Johnson");
		bookingBean.setBookedWhen(bookedWhen);
		bookingBean.setStartTime(DateConverterUtil.toDate("02:00 PM",
				DateConverterUtil.PATTERN_HH_MM_MERIDIAN));
		bookingBean.setEndTime(DateConverterUtil.toDate("03:30 PM",
				DateConverterUtil.PATTERN_HH_MM_MERIDIAN));
		bookingBean.setId("562ba47c44ae605f13522a83");
		bookingBean.setRoomName(MeetingRooms.PINNACLE.name());
		bookingBean.setStatus(BookingStatus.BOOKED);
		bookingBean.setTeamName("CVoS");

		// WHEN
		bookingValidationService.checkForClash(bookingTo, bookingBean);

		// THEN
	}

}
