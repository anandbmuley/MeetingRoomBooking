package com.merobo.utils;

import java.text.ParseException;

import com.merobo.beans.Booking;
import com.merobo.dtos.BookingDto;

public abstract class BeanCreatorUtil {

	public static Booking createBookingBean(BookingDto bookingDto)
			throws ParseException {
		Booking booking = new Booking();
//		bookingBean.setBookedBy(bookingTo.getBookedBy());
//		bookingBean.setBookedWhen(DateConverterUtil.toString(new Date(),
//				DateConverterUtil.PATTERN_HH_MM_MERIDIAN));
//		bookingBean.setEndTime(DateConverterUtil.toDate(bookingTo.getEndTime(),
//				DateConverterUtil.PATTERN_HH_MM_MERIDIAN));
//		bookingBean.setStartTime(DateConverterUtil.toDate(
//				bookingTo.getStartTime(),
//				DateConverterUtil.PATTERN_HH_MM_MERIDIAN));
//		bookingBean.setTeamName(bookingTo.getTeamName());
//		bookingBean.setRoomName(bookingTo.getRoomName());
//		bookingBean.setStatus(BookingStatus.BOOKED);
		return booking;
	}

}
