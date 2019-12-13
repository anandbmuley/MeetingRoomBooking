package com.merobo.services;

import com.merobo.beans.Booking;
import com.merobo.dtos.BookingDto;
import com.merobo.exceptions.BookingValidationServiceException;
import com.merobo.exceptions.booking.BookingClashesException;
import com.merobo.exceptions.booking.EndTimeClashesException;
import com.merobo.exceptions.booking.StartTimeClashesException;
import com.merobo.repositories.BookingRepository;
import com.merobo.utils.BookingStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingValidationService {

    @Autowired
    private BookingRepository bookingRepository;

    public void validateBooking(BookingDto bookingTo) throws BookingValidationServiceException {
        List<Booking> existingBookings = bookingRepository.findByRoomIdAndStatus(bookingTo.getRoomId(), BookingStatus.BOOKED)
                .stream()
                .collect(Collectors.toList());
        for (Booking bookingBean : existingBookings) {
            checkForClash(bookingTo, bookingBean);
        }
    }

    private void checkForClash(BookingDto needToBook, Booking existingBooking) throws BookingClashesException {
        if (needToBook.getStartDateTime().isAfter(existingBooking.getStartTime())
                && needToBook.getStartDateTime().isBefore(existingBooking.getEndTime())) {
            throw new StartTimeClashesException(existingBooking.toJSON());
        } else if (needToBook.getEndDateTime().isAfter(existingBooking.getStartTime())
                && needToBook.getEndDateTime().isBefore(existingBooking.getEndTime())) {
            throw new EndTimeClashesException(existingBooking.toJSON());
        } else if (needToBook.getStartDateTime().equals(existingBooking.getStartTime())
                && needToBook.getStartDateTime().equals(existingBooking.getEndTime())) {
            throw new StartTimeClashesException(existingBooking.toJSON());
        } else if (
                needToBook.getEndDateTime().equals(existingBooking.getStartTime())
                        || needToBook.getEndDateTime().equals(existingBooking.getEndTime())) {
            throw new EndTimeClashesException(existingBooking.toJSON());
        } else if (existingBooking.getEndTime().isAfter(needToBook.getStartDateTime())
                && existingBooking.getEndTime().isBefore(needToBook.getEndDateTime())) {
            throw new EndTimeClashesException(existingBooking.toJSON());
        }
    }
}
