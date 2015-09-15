package com.merobo.resources;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.jmock.Expectations;
import org.springframework.test.util.ReflectionTestUtils;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.merobo.actions.BookRoomAction;
import com.merobo.config.RootConfig;
import com.merobo.dataproviders.BookingTestDataProvider;
import com.merobo.dtos.BookingTo;
import com.merobo.dtos.MeetingRoomTo;
import com.merobo.services.BookingService;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.core.DefaultResourceConfig;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.LowLevelAppDescriptor;

public class BookingResourceTest extends JerseyTest implements RootConfig {

	private BookingService mockBookingService;

	private BookingResource bookingResource;
	private LowLevelAppDescriptor descriptor;

	public BookingResourceTest() {

	}

	@BeforeClass
	public void setUp() throws Exception {
		super.setUp();
	}

	@BeforeTest
	public void setData() {
		bookingResource = new BookingResource();
		mockBookingService = context.mock(BookingService.class);
		ReflectionTestUtils.setField(bookingResource, "bookingService",
				mockBookingService);
		descriptor.getResourceConfig().getSingletons().add(bookingResource);
	}

	@Override
	protected AppDescriptor configure() {
		descriptor = new LowLevelAppDescriptor.Builder(
				new DefaultResourceConfig()).build();
		return descriptor;
	}

	@AfterClass
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void shouldAddABooking() {
		// GIVEN
		final BookingTo bookingTo = BookingTestDataProvider.bookRoom();
		final BookRoomAction bookRoomAction = new BookRoomAction();

		context.checking(new Expectations() {
			{
				oneOf(mockBookingService).bookRoom(with(bookingTo));
				will(bookRoomAction);
			}
		});
		// WHEN
		ClientResponse response = resource().path("booking/add")
				.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_JSON)
				.post(ClientResponse.class, bookingTo);

		String bookingId = response.getEntity(String.class);
		// THEN
		Assert.assertEquals(response.getStatus(), 200);
		Assert.assertEquals(bookingId, "BID101");
	}

	@Test
	public void shouldGetAllBookings() {
		// GIVEN

		final List<BookingTo> allBookings = BookingTestDataProvider.getAll();

		context.checking(new Expectations() {
			{
				oneOf(mockBookingService).getAll();
				will(returnValue(allBookings));
			}
		});

		// WHEN
		ClientResponse response = resource().path("booking/list")
				.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		MeetingRoomTo meetingRoomTo = response.getEntity(MeetingRoomTo.class);

		// THEN
		Assert.assertEquals(meetingRoomTo.getPinnacle().size(), 2);
	}
}
