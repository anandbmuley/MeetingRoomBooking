package com.merobo.utils

import com.merobo.beans.BookingBean
import com.merobo.builders.BookingToBuilder
import com.merobo.common.AssertionsCatalogue
import com.merobo.dtos.BookingTo
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test

import java.text.ParseException

@Test
class BeanCreatorUtilTest {
    private AssertionsCatalogue assertionsCatalogue
    private BookingToBuilder bookingToBuilder

    @BeforeClass
    public void setUp() {
        bookingToBuilder = new BookingToBuilder()
        assertionsCatalogue = new AssertionsCatalogue()
    }

    @Test
    public void ShouldSetStartTimeToToday() throws ParseException {
        // GIVEN
        BookingTo bookingTo = bookingToBuilder.setStartTime("09:00 am").setEndTime("10:00 am").setBookedWhen("08:00 am")
                .build()

        // WHEN
        BookingBean actual = BeanCreatorUtil.createBookingBean(bookingTo)

        // THEN
        assertionsCatalogue.assertTodaysDate(actual.getStartTime())
    }
}
