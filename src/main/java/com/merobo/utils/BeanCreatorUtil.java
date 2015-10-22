package com.merobo.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.merobo.beans.BookingBean;
import com.merobo.dtos.BookingTo;

public abstract class BeanCreatorUtil {

	public static BookingBean createBookingBean(BookingTo bookingTo) {
		BookingBean bookingBean = new BookingBean();
		bookingBean.setBookedBy(bookingTo.getBookedBy());
		bookingBean.setBookedWhen(DateConverterUtil.toString(new Date(),
				DateConverterUtil.PATTERN_HH_MM_MERIDIAN));
		bookingBean.setEndTime(bookingTo.getEndTime());
		bookingBean.setStartTime(bookingTo.getStartTime());
		bookingBean.setTeamName(bookingTo.getTeamName());
		bookingBean.setRoomName(bookingTo.getRoomName());
		return bookingBean;
	}

	public static List<BookingTo> createBookingTos(
			List<BookingBean> bookingBeans) {
		List<BookingTo> bookingTos = new ArrayList<BookingTo>();
		for (BookingBean bookingBean : bookingBeans) {
			BookingTo bookingTo = new BookingTo();
			bookingTo.setBookedBy(bookingBean.getBookedBy());
			bookingTo.setBookedWhen(bookingBean.getBookedWhen());
			bookingTo.setEndTime(bookingBean.getEndTime());
			bookingTo.setId(bookingBean.getId());
			bookingTo.setStartTime(bookingBean.getStartTime());
			bookingTo.setTeamName(bookingBean.getTeamName());
			bookingTo.setRoomName(bookingBean.getRoomName());
			bookingTos.add(bookingTo);
		}
		return bookingTos;
	}

}
