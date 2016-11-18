package com.merobo.utils

import com.merobo.beans.BookingBean
import com.merobo.builders.BookingToBuilder
import com.merobo.common.AssertionsCatalogue
import com.merobo.dtos.BookingTo
import spock.lang.Specification

class BeanCreatorUtilTest extends Specification{
    private AssertionsCatalogue assertionsCatalogue
    private BookingToBuilder bookingToBuilder

    def setup() {
        bookingToBuilder = new BookingToBuilder()
        assertionsCatalogue = new AssertionsCatalogue()
    }

    def "createBookingBean - should set StartTime to Today"(){
        given:"a bookingTo object with startTime"
        BookingTo bookingTo = bookingToBuilder.setStartTime("09:00 am").setEndTime("10:00 am").setBookedWhen("08:00 am")
                .build()

        when:"createBookingBean method is called"
        BookingBean actual = BeanCreatorUtil.createBookingBean(bookingTo)

        then:"bean contains today's date"
        assertionsCatalogue.assertTodaysDate(actual.getStartTime())
    }
}
