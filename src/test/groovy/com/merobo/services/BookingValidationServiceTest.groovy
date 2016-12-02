package com.merobo.services

import com.merobo.beans.BookingBean
import com.merobo.builders.BookingBeanBuilder
import com.merobo.builders.BookingToBuilder
import com.merobo.dtos.BookingTo
import com.merobo.exceptions.booking.EndTimeClashesException
import com.merobo.exceptions.booking.StartTimeClashesException
import com.merobo.repositories.BookingRepository
import spock.lang.Ignore
import spock.lang.Specification

class BookingValidationServiceTest extends Specification {

    BookingValidationService bookingValidationService
    BookingRepository mockBookingRepository

    def setup() {
        mockBookingRepository = Mock(BookingRepository)
        bookingValidationService = new BookingValidationService(
                bookingRepository: mockBookingRepository
        )
    }

    def "validateBooking - should validate Booking successfully"() {
        given:"a valid booking details"
        BookingTo bookingTo = new BookingToBuilder().build()
        final List<BookingBean> bookingBeans = [new BookingBeanBuilder().build()]
        when:"validateBooking method is called"
        bookingValidationService.validateBooking(bookingTo)

        then:"no exception is thrown"
        1 * mockBookingRepository.findByRoomName(bookingTo.roomName) >> {bookingBeans}
        0 * _
    }

    def "checkForClash - should not clash booking"(){
        given:"a booking which is not clashing"
        BookingTo bookingTo = new BookingToBuilder()
                .setStartTime("04:00 PM")
                .setEndTime("05:00 PM").build()
        BookingBean bookingBean = new BookingBeanBuilder().build()

        when:"checkForClash method is called"
        bookingValidationService.checkForClash(bookingTo, bookingBean)

        then:"booking is not clashed"
        0 * _
    }

    def "checkForClash - should clash StartTime between booking"(){
        given:"a booking which clashes with another booking"
        BookingTo bookingTo = new BookingToBuilder()
                .setStartTime("02:01 PM")
                .setEndTime("03:01 PM").build()
        BookingBean bookingBean = new BookingBeanBuilder().build()

        when:"checkForClash method is called"
        bookingValidationService.checkForClash(bookingTo, bookingBean)

        then:"StartTimeClashesException is thrown"
        thrown(StartTimeClashesException)
        0 * _
    }

    def "checkForClash - should clash EndTime between booking"(){
        given:"a booking of which end time clashes"
        BookingTo bookingTo = new BookingToBuilder()
                .setStartTime("01:00 PM")
                .setEndTime("02:10 PM").build()
        BookingBean bookingBean = new BookingBeanBuilder().build()

        when:"checkForClash method is called"
        bookingValidationService.checkForClash(bookingTo, bookingBean)

        then:"EndTimeClashesException exception is thrown"
        thrown(EndTimeClashesException)
        0 * _
    }

    def "checkForClash - should clash EndTime of existing Booking"(){
        given:"End time of existing booking lies between new booking start time and end time"
        BookingTo bookingTo = new BookingToBuilder()
                .setStartTime("01:59 PM")
                .setEndTime("03:10 PM").build()
        BookingBean bookingBean = new BookingBeanBuilder().build()

        when:"checkForClash is called"
        bookingValidationService.checkForClash(bookingTo, bookingBean)

        then:"EndTimeClashesException exception is thrown"
        thrown(EndTimeClashesException)
        0 * _
    }

    @Ignore
    def "checkForClash - should clash StartTime equals existing meeting StartTime"(){
        given:"Start Time Existing Booking StartTime"
        BookingTo bookingTo = new BookingToBuilder()
                .setStartTime("02:00 PM")
                .setEndTime("03:20 PM").build()
        BookingBean bookingBean = new BookingBeanBuilder().build()

        when:"checkForClash method is called"
        bookingValidationService.checkForClash(bookingTo, bookingBean)

        then:"StartTimeClashesException is thrown"
        thrown(EndTimeClashesException)
        0 * _
    }

    def "checkForClash - should clash StartTime equals other meeting EndTime"(){
        given:"a booking which clashes the start time equals other meeting EndTime"
        BookingTo bookingTo = new BookingToBuilder()
                .setStartTime("03:00 PM")
                .setEndTime("03:30 PM").build()
        BookingBean bookingBean = new BookingBeanBuilder().build()

        when:"checkForClash method is called"
        bookingValidationService.checkForClash(bookingTo, bookingBean)

        then:"StartTimeClashesException is thrown"
        thrown(StartTimeClashesException)
        0 * _
    }

    def "checkForClash - should not clash EndTime equals other meeting StartTime"(){
        given:"a booking which does not clash"
        BookingTo bookingTo = new BookingToBuilder()
                .setStartTime("01:00 PM")
                .setEndTime("02:00 PM").build()
        BookingBean bookingBean = new BookingBeanBuilder().build()

        when:"checkForClash method is called"
        bookingValidationService.checkForClash(bookingTo, bookingBean)

        then:"no exception is thrown"
        0 * _
    }

    def "checkForClash - should clash EndTime equals other meeting EndTime"(){
        given:"a booking of which end time clashes other meeting endtime"
        BookingTo bookingTo = new BookingToBuilder()
                .setStartTime("01:00 PM")
                .setEndTime("03:00 PM").build()
        BookingBean bookingBean = new BookingBeanBuilder().build()

        when:"checkForClash method is called"
        bookingValidationService.checkForClash(bookingTo, bookingBean)

        then:"EndTimeClashesException is thrown"
        thrown(EndTimeClashesException)
        0 * _
    }
}