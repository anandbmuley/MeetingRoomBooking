package com.merobo.services;

import com.merobo.beans.BookingBean;
import com.merobo.dtos.BookingTo;
import com.merobo.exceptions.BookingValidationServiceException;
import com.merobo.exceptions.booking.BookingClashesException;
import com.merobo.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingValidationService {

    @Autowired
    private BookingRepository bookingRepository;

    public void validateBooking(BookingTo bookingTo) throws BookingValidationServiceException {
//        List<BookingBean> existingBookings = bookingRepository.findByRoomName(bookingTo.getRoomName()).stream().filter(booking -> BookingStatus.BOOKED.equals(booking.getStatus())).collect(Collectors.toList());
//        for (BookingBean bookingBean : existingBookings) {
//            checkForClash(bookingTo, bookingBean);
//        }
    }

    public void checkForClash(BookingTo bookingTo, BookingBean bookingBean) throws BookingClashesException {
//        if (bookingTo.getStartDateTime().after(bookingBean.getStartTime())
//                && bookingTo.getStartDateTime().before(bookingBean.getEndTime())) {
//            throw new StartTimeClashesException(bookingBean.toJSON());
//        } else if (bookingTo.getEndDateTime().after(bookingBean.getStartTime())
//                && bookingTo.getEndDateTime().before(bookingBean.getEndTime())) {
//            throw new EndTimeClashesException(bookingBean.toJSON());
//        } else if (bookingTo.getStartDateTime().equals(bookingBean.getStartTime())
//                || bookingTo.getStartDateTime().equals(bookingBean.getEndTime())) {
//            throw new StartTimeClashesException(bookingBean.toJSON());
//        } else if (bookingTo.getEndDateTime().equals(bookingBean.getStartTime())
//                || bookingTo.getEndDateTime().equals(bookingBean.getEndTime())) {
//            throw new EndTimeClashesException(bookingBean.toJSON());
//        } else if (bookingBean.getEndTime().after(bookingTo.getStartDateTime())
//                && bookingBean.getEndTime().before(bookingTo.getEndDateTime())) {
//            throw new EndTimeClashesException(bookingBean.toJSON());
//        }
    }
}
