package com.merobo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.merobo.beans.BookingBean;
import com.merobo.utils.BookingStatus;

@Component
public class BookingFilter {

	public List<BookingBean> filterCancelled(List<BookingBean> bookingBeans) {
		List<BookingBean> filtered = new ArrayList<BookingBean>();
		for (BookingBean bookingBean : bookingBeans) {
			if (BookingStatus.BOOKED.equals(bookingBean.getStatus())) {
				filtered.add(bookingBean);
			}
		}
		return filtered;
	}

}
