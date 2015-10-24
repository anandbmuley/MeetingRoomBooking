package com.merobo.services;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.merobo.beans.BookingBean;
import com.merobo.dtos.BookingTo;
import com.merobo.dtos.MeetingRoomTo;
import com.merobo.exceptions.BookingServiceException;
import com.merobo.exceptions.BookingValidationServiceException;
import com.merobo.repositories.BookingRepository;
import com.merobo.utils.BeanCreatorUtil;
import com.merobo.utils.BookingStatus;
import com.merobo.utils.DtoCreatorUtil;
import com.merobo.utils.MeetingRooms;

@Service
public class BookingService {

	@Autowired
	protected BookingRepository bookingRepository;

	@Autowired
	private BookingValidationService bookingValidationService;

	public void cancelBooking(String bookingId) {
		BookingBean bookingBean = bookingRepository.findOne(bookingId);
		bookingBean.setStatus(BookingStatus.CANCELLED);
		bookingRepository.save(bookingBean);
	}

	public BookingTo bookRoom(BookingTo bookingTo)
			throws BookingServiceException {
		try {
			bookingValidationService.validateBooking(bookingTo);
			BookingBean bookingBean = BeanCreatorUtil
					.createBookingBean(bookingTo);
			bookingRepository.save(bookingBean);
			bookingTo.setId(bookingBean.getId());
		} catch (ParseException e) {
			throw new BookingServiceException(e);
		} catch (BookingValidationServiceException e) {
			throw new BookingServiceException(e);
		}

		return bookingTo;
	}

	public MeetingRoomTo getAll() {
		List<BookingBean> beans = bookingRepository.findAll();
		List<BookingTo> bookingTos = DtoCreatorUtil.createBookingTos(beans);

		MeetingRoomTo meetingRoomTo = new MeetingRoomTo();
		for (BookingTo bookingTo : bookingTos) {
			if (BookingStatus.CANCELLED.equals(bookingTo.getStatus())) {
				continue;
			}
			if (MeetingRooms.PINNACLE.name().equals(bookingTo.getRoomName())) {
				meetingRoomTo.getPinnacle().add(bookingTo);
			} else if (MeetingRooms.OTHER.name()
					.equals(bookingTo.getRoomName())) {
				meetingRoomTo.getOther().add(bookingTo);
			}
		}
		return meetingRoomTo;
	}
}
