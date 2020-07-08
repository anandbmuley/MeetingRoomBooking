package com.merobo.resources

import abm.jsonizedut.TestDataLoader
import com.merobo.dtos.BookingDto
import com.merobo.services.BookingService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Specification

class BookingResourceSpec extends Specification {

    BookingResource resource

    BookingService mockBookingService

    TestDataLoader testDataLoader

    void setup() {
        testDataLoader = TestDataLoader.getInstance()
        mockBookingService = Mock(BookingService)
        resource = new BookingResource(mockBookingService)
    }

    def "GetCurrentBookings"() {
    }

    def "Cancel"() {
    }

    def "BookRoom"() {
        given:
        BookingDto bookingDto = testDataLoader.readRequest("create-booking-request.json", BookingDto)

        1 * mockBookingService.bookRoom({BookingDto it ->
            it.id = UUID.randomUUID().toString()
            assert it.bookedById == "John Doe"
            true
        })

        when:
        ResponseEntity actual = resource.bookRoom("123", bookingDto)

        then:
        actual.statusCode == HttpStatus.CREATED
    }

    def "GetAllBookings"() {
    }

    def "GetTodaysBookings"() {
    }

    def "GetBookingsFor"() {
    }
}
