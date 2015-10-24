package com.merobo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.merobo.beans.BookingBean;
import com.merobo.dtos.BookingTo;
import com.merobo.exceptions.BookingValidationServiceException;
import com.merobo.exceptions.booking.BookingClashesException;
import com.merobo.exceptions.booking.EndTimeClashesException;
import com.merobo.exceptions.booking.StartTimeClashesException;
import com.merobo.repositories.BookingRepository;

@Service
public class BookingValidationService {

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private BookingFilter bookingFilter;

	public void validateBooking(BookingTo bookingTo)
			throws BookingValidationServiceException {
		List<BookingBean> existingBookings = bookingFilter
				.filterCancelled(bookingRepository.findByRoomName(bookingTo
						.getRoomName()));
		for (BookingBean bookingBean : existingBookings) {
			checkForClash(bookingTo, bookingBean);
		}
	}

	public void checkForClash(BookingTo bookingTo, BookingBean bookingBean)
			throws BookingClashesException {
		if (bookingTo.getStartDateTime().after(bookingBean.getStartTime())
				&& bookingTo.getStartDateTime()
						.before(bookingBean.getEndTime())) {
			throw new StartTimeClashesException(bookingBean.toJSON());
		} else if (bookingTo.getEndDateTime().after(bookingBean.getStartTime())
				&& bookingTo.getEndDateTime().before(bookingBean.getEndTime())) {
			throw new EndTimeClashesException(bookingBean.toJSON());
		} else if (bookingTo.getStartDateTime().equals(
				bookingBean.getStartTime())
				|| bookingTo.getEndDateTime().equals(bookingBean.getEndTime())) {
			throw new StartTimeClashesException(bookingBean.toJSON());
		} else if (bookingTo.getEndDateTime()
				.equals(bookingBean.getStartTime())
				|| bookingTo.getEndDateTime().equals(bookingBean.getEndTime())) {
			throw new EndTimeClashesException(bookingBean.toJSON());
		}
	}
}
