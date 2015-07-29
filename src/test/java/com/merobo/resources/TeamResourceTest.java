package com.merobo.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.hamcrest.Description;
import org.jmock.Expectations;
import org.jmock.api.Expectation;
import org.jmock.api.Invocation;
import org.springframework.test.util.ReflectionTestUtils;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.merobo.beans.BookingBean;
import com.merobo.beans.TeamBean;
import com.merobo.config.RootConfig;
import com.merobo.dtos.TeamTo;
import com.merobo.repositories.BookingRepositories;
import com.merobo.repositories.TeamRepository;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.core.DefaultResourceConfig;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.LowLevelAppDescriptor;

public class TeamResourceTest extends JerseyTest implements RootConfig {

	
	
	
	private LowLevelAppDescriptor descriptor;
	private TeamResource teamResource;
	private TeamRepository mockTeamRepositories;
	private Object teamTo;

	public TeamResourceTest() {
		
	}

	@BeforeClass
	public void setUp() throws Exception {
		super.setUp();
	}

	@AfterClass
	public void tearDown() throws Exception {
		super.tearDown();
	}
	
	@BeforeTest
	public void setData() {
		teamResource = new TeamResource();
		mockTeamRepositories = context.mock(TeamRepository.class);
		ReflectionTestUtils.setField(teamResource, "teamRepository",
				mockTeamRepositories);
		descriptor.getResourceConfig().getSingletons().add(teamResource);
	}

	@Override
	protected AppDescriptor configure() {
		descriptor = new LowLevelAppDescriptor.Builder(
				new DefaultResourceConfig()).build();
		return descriptor;
	}
	

	@Test
	public void shouldFindTeam() {
		// GIVEN
		int expecteStatus = 200;
		final String name = "MCS";
		final TeamBean teamBean = new TeamBean();
		teamBean.setName(name);

		context.checking(new Expectations() {
			{
				oneOf(mockTeamRepositories).findByName(with(name));
				will(returnValue(teamBean));
			}
		});

		// WHEN
		WebResource resource = resource().path("team/find").queryParam("name",
				name);
		ClientResponse actualClientResponse = resource
				.get(ClientResponse.class);

		// THEN
		Assert.assertEquals(actualClientResponse.getStatus(), expecteStatus);
	}

	@Test
	public void shouldListTeam() {
		// GIVEN
		int expecteStatus = 200;
		final List<TeamBean> list =new ArrayList<TeamBean>();
		context.checking(new Expectations() {
			{
				oneOf(mockTeamRepositories).findAll();
				will(returnValue(list));
			}
		});
		
		// WHEN
		WebResource resource = resource().path("team/list");
		ClientResponse actualClientResponse = resource
				.get(ClientResponse.class);

		// THEN
		Assert.assertEquals(actualClientResponse.getStatus(), expecteStatus);
	}

	@Test
	public void shouldAddTeam() {

		final TeamBean teamBean=new TeamBean();
		TeamTo teamTo=new TeamTo();
		teamTo.setName("akshay");
		teamBean.setName(teamTo.getName());
		// GIVEN
		 int expecteStatus = 200;
		context.checking(new Expectations(){
			

			{
				oneOf(mockTeamRepositories).save(teamBean);
				will(returnValue(teamBean));
			}
		});
		
		
		// WHEN
		WebResource service = resource().path("team/add");
		ClientResponse resp = service.type(MediaType.APPLICATION_JSON).post(
				ClientResponse.class, teamTo);
		System.out.println("Got stuff: " + resp);
		String text = resp.getEntity(String.class);
		System.out.println("Got Entity"+text);
		// THEN
		Assert.assertEquals(200, resp.getStatus());

	}

	@Test
	public void shouldDeleteTeam() {
		// GIVEN
		final String name="akshay";
		final List<TeamBean> list =new ArrayList<TeamBean>();
		TeamTo teamTo =new TeamTo();
		teamTo.setName(name);
		context.checking(new Expectations(){
			{
				oneOf(mockTeamRepositories).deleteByName(with(name));
				returnValue(list);
			}
		});
		// WHEN

		WebResource service = resource().path("team/delete");
		ClientResponse resp = service.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_JSON)
				.delete(ClientResponse.class, teamTo);
		System.out.println("Got stuff: " + resp);
		// THEN
		Assert.assertEquals(200, resp.getStatus());

	}

}
