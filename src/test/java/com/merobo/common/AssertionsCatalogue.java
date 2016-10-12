package com.merobo.common;

import java.util.Calendar;
import java.util.Date;

import org.testng.Assert;

public class AssertionsCatalogue {

	public void assertTodaysDate(Date actual) {
		Calendar actualCalendar = Calendar.getInstance();
		actualCalendar.setTime(actual);
		Calendar expectedCalendar = Calendar.getInstance();
		Assert.assertEquals(actualCalendar.get(Calendar.DAY_OF_MONTH), expectedCalendar.get(Calendar.DAY_OF_MONTH),
				"Day of month mismatched");
		Assert.assertEquals(actualCalendar.get(Calendar.MONTH), expectedCalendar.get(Calendar.MONTH),
				"Month number mismatched");
		Assert.assertEquals(actualCalendar.get(Calendar.YEAR), expectedCalendar.get(Calendar.YEAR), "Year mismatched");

	}

}
