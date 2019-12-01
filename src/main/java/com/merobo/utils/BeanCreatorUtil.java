package com.merobo.utils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import com.merobo.beans.BookingBean;
import com.merobo.dtos.BookingTo;

public abstract class BeanCreatorUtil {

	public static BookingBean createBookingBean(BookingTo bookingTo)
			throws ParseException {
		BookingBean bookingBean = new BookingBean();
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
		return bookingBean;
	}

}
