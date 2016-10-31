package com.merobo.services

import com.merobo.beans.BookingBean
import com.merobo.builders.BookingBeanBuilder
import com.merobo.builders.BookingToBuilder
import com.merobo.common.RootTestConfig
import com.merobo.dtos.BookingTo
import com.merobo.exceptions.BookingValidationServiceException
import com.merobo.exceptions.booking.EndTimeClashesException
import com.merobo.exceptions.booking.StartTimeClashesException
import com.merobo.repositories.BookingRepository
import org.jmock.Expectations
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test

import java.text.ParseException

class BookingValidationServiceTest extends RootTestConfig {

    BookingValidationService bookingValidationService
    BookingRepository mockBookingRepository

    @BeforeClass
    public void setUp() {
        mockBookingRepository = context.mock(BookingRepository)
        bookingValidationService = new BookingValidationService(
                bookingRepository: mockBookingRepository
        )
    }

    @Test
    public void ShouldValidateBookingSuccessfully() {
        // GIVEN
        BookingTo bookingTo = new BookingToBuilder().build()
        final List<BookingBean> bookingBeans = [new BookingBeanBuilder().build()]
        context.checking(new Expectations() {
            {
                oneOf(mockBookingRepository).findByRoomName(bookingTo.roomName)
                will(returnValue(bookingBeans))
            }
        })

        // WHEN
        bookingValidationService.validateBooking(bookingTo)

        // THEN
    }

    @Test
    public void ShouldNotClashBooking() throws BookingValidationServiceException, ParseException {
        // GIVEN
        BookingTo bookingTo = new BookingToBuilder()
                .setStartTime("04:00 PM")
                .setEndTime("05:00 PM").build()
        BookingBean bookingBean = new BookingBeanBuilder().build()

        // WHEN
        bookingValidationService.checkForClash(bookingTo, bookingBean)

        // THEN
    }

    @Test(expectedExceptions = StartTimeClashesException)
    public void ShouldClashStartTimeBetweenBooking() throws BookingValidationServiceException, ParseException {
        // GIVEN
        BookingTo bookingTo = new BookingToBuilder()
                .setStartTime("02:01 PM")
                .setEndTime("03:01 PM").build()
        BookingBean bookingBean = new BookingBeanBuilder().build()

        // WHEN
        bookingValidationService.checkForClash(bookingTo, bookingBean)

        // THEN
    }

    @Test(expectedExceptions = EndTimeClashesException)
    public void ShouldClashEndTimeBetweenBooking() throws BookingValidationServiceException, ParseException {
        // GIVEN
        BookingTo bookingTo = new BookingToBuilder()
                .setStartTime("01:00 PM")
                .setEndTime("02:10 PM").build()
        BookingBean bookingBean = new BookingBeanBuilder().build()

        // WHEN
        bookingValidationService.checkForClash(bookingTo, bookingBean)

        // THEN
    }

    @Test(expectedExceptions = EndTimeClashesException)
    public void ShouldClashEndTimeOfExistingBooking() throws BookingValidationServiceException, ParseException {
        // GIVEN : End time of existing booking lies between new booking start time and end time
        BookingTo bookingTo = new BookingToBuilder()
                .setStartTime("01:59 PM")
                .setEndTime("03:10 PM").build()
        BookingBean bookingBean = new BookingBeanBuilder().build()

        // WHEN
        bookingValidationService.checkForClash(bookingTo, bookingBean)

        // THEN
    }

    @Test(expectedExceptions = StartTimeClashesException)
    public void ShouldClashStartTimeEqualsExistingMeetingStartTime() throws BookingValidationServiceException, ParseException {
        // GIVEN : Start Time Existing Booking StartTime
        BookingTo bookingTo = new BookingToBuilder()
                .setStartTime("02:00 PM")
                .setEndTime("03:20 PM").build()
        BookingBean bookingBean = new BookingBeanBuilder().build()

        // WHEN
        bookingValidationService.checkForClash(bookingTo, bookingBean)

        // THEN
    }

    @Test(expectedExceptions = StartTimeClashesException)
    public void ShouldClashStartTimeEqualsOtherMeetingEndTime() throws BookingValidationServiceException, ParseException {
        // GIVEN
        BookingTo bookingTo = new BookingToBuilder()
                .setStartTime("03:00 PM")
                .setEndTime("03:30 PM").build()
        BookingBean bookingBean = new BookingBeanBuilder().build()

        // WHEN
        bookingValidationService.checkForClash(bookingTo, bookingBean)

        // THEN
    }

    @Test
    public void ShouldNotClashEndTimeEqualsOtherMeetingStartTime() throws BookingValidationServiceException, ParseException {
        // GIVEN
        BookingTo bookingTo = new BookingToBuilder()
                .setStartTime("01:00 PM")
                .setEndTime("02:00 PM").build()
        BookingBean bookingBean = new BookingBeanBuilder().build()

        // WHEN
        bookingValidationService.checkForClash(bookingTo, bookingBean)

        // THEN
    }

    @Test(expectedExceptions = EndTimeClashesException)
    public void ShouldClashEndTimeEqualsOtherMeetingEndTime() throws BookingValidationServiceException, ParseException {
        // GIVEN
        BookingTo bookingTo = new BookingToBuilder()
                .setStartTime("01:00 PM")
                .setEndTime("03:00 PM").build()
        BookingBean bookingBean = new BookingBeanBuilder().build()

        // WHEN
        bookingValidationService.checkForClash(bookingTo, bookingBean)

        // THEN
    }
}