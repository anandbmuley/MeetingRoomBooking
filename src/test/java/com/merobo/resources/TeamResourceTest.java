package com.merobo.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jmock.Expectations;
import org.springframework.test.util.ReflectionTestUtils;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.merobo.beans.TeamBean;
import com.merobo.config.RootConfig;
import com.merobo.dataproviders.TeamTestDataProvider;
import com.merobo.dtos.TeamTo;
import com.merobo.services.TeamService;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.core.DefaultResourceConfig;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.LowLevelAppDescriptor;

public class TeamResourceTest extends JerseyTest implements RootConfig {

	private LowLevelAppDescriptor descriptor;
	private TeamResource teamResource;
	private TeamService mockTeamService;

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
		mockTeamService = context.mock(TeamService.class);
		ReflectionTestUtils.setField(teamResource, "teamService",
				mockTeamService);
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
		final String name = "Maveric";
		final TeamTo teamTo = TeamTestDataProvider.createTeamTo();

		context.checking(new Expectations() {
			{
				oneOf(mockTeamService).findTeam(with(name));
				will(returnValue(teamTo));
			}
		});

		// WHEN
		WebResource resource = resource().path("team/find").queryParam("name",
				name);
		ClientResponse actualClientResponse = resource
				.get(ClientResponse.class);
		TeamTo actual = actualClientResponse.getEntity(TeamTo.class);

		// THEN
		Assert.assertEquals(actual.getName(), name);
	}

	@Test
	public void shouldListTeam() {
		// GIVEN
		int expecteStatus = 200;
		final List<TeamBean> list = new ArrayList<TeamBean>();
		context.checking(new Expectations() {
			{
				oneOf(mockTeamService).getAllTeams();
				will(returnValue(list));
			}
		});

		// WHEN
		ClientResponse actualClientResponse = resource().path("team/list").get(
				ClientResponse.class);

		// THEN
		Assert.assertEquals(actualClientResponse.getStatus(), expecteStatus);
	}

	@Test
	public void shouldAddTeam() {

		final TeamTo teamTo = TeamTestDataProvider.createTeamTo();
		final TeamTo updatedTeamTo = new TeamTo();
		teamTo.setName(teamTo.getName());
		teamTo.setId("55efe101e4b03091c264fcd3");

		context.checking(new Expectations() {
			{
				oneOf(mockTeamService).addTeam(with(teamTo));
				will(returnValue(updatedTeamTo));
			}
		});

		// WHEN
		ClientResponse resp = resource().path("team/add")
				.type(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.post(ClientResponse.class, teamTo);

		// THEN
		Assert.assertEquals(resp.getStatus(),
				Response.Status.CREATED.getStatusCode(),
				"Team Should Be Added Successfully");

	}

	@Test
	public void shouldDeleteTeam() {
		// GIVEN
		final String name = "akshay";
		final List<TeamBean> list = new ArrayList<TeamBean>();
		final TeamTo teamTo = new TeamTo();
		teamTo.setName(name);
		context.checking(new Expectations() {
			{
				oneOf(mockTeamService).deleteTeam(with(teamTo));
				returnValue(list);
			}
		}); // WHEN

		ClientResponse resp = resource().path("team/delete")
				.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_JSON)
				.delete(ClientResponse.class, teamTo);
		Assert.assertEquals(200, resp.getStatus());

	}

}
