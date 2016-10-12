package com.merobo.utils;

import java.text.ParseException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.merobo.beans.BookingBean;
import com.merobo.builders.BookingBuilder;
import com.merobo.common.AssertionsCatalogue;
import com.merobo.dtos.BookingTo;

@Test
public class BeanCreatorUtilTest {

	private AssertionsCatalogue assertionsCatalogue;
	private BookingBuilder bookingBuilder;

	@BeforeClass
	public void setUp() {
		bookingBuilder = new BookingBuilder();
		assertionsCatalogue = new AssertionsCatalogue();
	}

	@Test
	public void ShouldSetStartTimeToToday() throws ParseException {
		// GIVEN
		BookingTo bookingTo = bookingBuilder.setStartTime("09:00 am").setEndTime("10:00 am").setBookedWhen("08:00 am")
				.build();

		// WHEN
		BookingBean actual = BeanCreatorUtil.createBookingBean(bookingTo);

		// THEN
		assertionsCatalogue.assertTodaysDate(actual.getStartTime());

	}

}
