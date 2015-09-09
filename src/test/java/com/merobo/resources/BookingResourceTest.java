package com.merobo.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.jmock.Expectations;
import org.springframework.test.util.ReflectionTestUtils;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.merobo.beans.BookingBean;
import com.merobo.config.RootConfig;
import com.merobo.dtos.BookingTo;
import com.merobo.repositories.BookingRepository;
import com.merobo.services.BookingService;
import com.merobo.services.BookingServiceImpl;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
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
		/*
		 * mockBookingRepositories = context.mock(BookingRepositories.class);
		 * ReflectionTestUtils.setField(bookingResource, "bookingRepositories",
		 * mockBookingRepositories);
		 */
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
	public void shouldFindTeamBooking() {
		// GIVEN
		int expecteStatus = 200;
		final String name = "akshay1111111";
		final BookingBean bookingBean = new BookingBean();
		bookingBean.setTeam(name);
		final BookingTo bookingTo = new BookingTo();

		context.checking(new Expectations() {
			{
				oneOf(mockBookingService).findTeamBooking(with(name));
				will(returnValue(bookingTo));
			}
		});
		// WHEN
		WebResource resource = resource().path("booking/find").queryParam(
				"team", name);
		ClientResponse actualClientResponse = resource
				.get(ClientResponse.class);
		BookingTo actual = actualClientResponse.getEntity(BookingTo.class);

		// THEN
		Assert.assertEquals(actualClientResponse.getStatus(), expecteStatus);
		//Assert.assertEquals(actual.getTeam(), name);
	}
   
	 @Test
	public void shouldListTeamBooking() {
		// GIVEN
		int expecteStatus = 200;
		final List<BookingBean> list = new ArrayList<BookingBean>();

		// WHEN
		context.checking(new Expectations() {
			{
				oneOf(mockBookingService).findAll();
				will(returnValue(list));
			}
		});
		WebResource resource = resource().path("booking/list");
		ClientResponse actualClientResponse = resource
				.get(ClientResponse.class);

		// THEN
		Assert.assertEquals(actualClientResponse.getStatus(), expecteStatus);
	}

	@Test
	public void shouldAddTeamBooking() {

		int expecteStatus = 200;
		final String name = "MCS";
		final BookingBean bookingBean = new BookingBean();
		bookingBean.setTeam(name);
		final BookingTo bookingTo = new BookingTo();
		bookingTo.setTeam(name);
		/*
		 * bookingTo.setDate(bookingBean.getDate());
		 * bookingTo.setStartTime(bookingBean.getStartTime());
		 * bookingTo.setEndTime(bookingBean.getEndTime());
		 */
		context.checking(new Expectations() {
			{ /*
				oneOf(mockBookingRepositories).save(with(bookingBean));
				will(returnValue(bookingBean));      */                        
				oneOf(mockBookingService).bookRoom(with(bookingTo));
				will(returnValue(bookingTo));
			}
		});
		// WHEN
		System.out.println("before webresource");
		WebResource resource = resource().path("booking/bookroom");
		ClientResponse actualClientResponse = resource.type(
				MediaType.APPLICATION_JSON).post(ClientResponse.class,
				bookingTo);
		BookingTo actual = actualClientResponse.getEntity(BookingTo.class);

		// THEN
		Assert.assertEquals(actualClientResponse.getStatus(), expecteStatus);
		Assert.assertEquals(actual.getTeam(), name);

	}


	 @Test
	public void shouldDeleteTeamBooking() {
		// GIVEN
		final String name = "MCS";

		final List<BookingBean> list = new ArrayList<BookingBean>();
		BookingTo bookingTo = new BookingTo();
		bookingTo.setTeam(name);
		// WHEN
		context.checking(new Expectations() {
			{
				oneOf(mockBookingService).deleteTeam(with(name));
				will(returnValue(list));
			}
		});
		// WHEN
		WebResource resource = resource().path("booking/delete");
		ClientResponse actualClientResponse = resource.type(
				MediaType.APPLICATION_JSON).delete(ClientResponse.class,
				bookingTo);

		String actual = actualClientResponse.getEntity(String.class);

		// THEN
		// Assert.assertEquals(actual.getClass(), "java.lang.String");
		Assert.assertEquals(actualClientResponse.getStatus(), 200);

	}

}
