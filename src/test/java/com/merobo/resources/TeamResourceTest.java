package com.merobo.resources;

import javax.ws.rs.core.MediaType;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.merobo.dtos.TeamTo;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.test.framework.JerseyTest;

public class TeamResourceTest extends JerseyTest {

	public TeamResourceTest() {
		super("com.merobo.resources");
	}

	@BeforeClass
	public void setUp() throws Exception {
		super.setUp();
	}

	@AfterClass
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void shouldFindTeam() {
		// GIVEN
		int expecteStatus = 200;

		// WHEN
		WebResource resource = resource().path("team/find").queryParam("name",
				"akshay11");
		ClientResponse actualClientResponse = resource
				.get(ClientResponse.class);

		// THEN
		Assert.assertEquals(actualClientResponse.getStatus(), expecteStatus);
	}

	@Test
	public void shouldListTeam() {
		// GIVEN
		int expecteStatus = 200;

		// WHEN
		WebResource resource = resource().path("team/list");
		ClientResponse actualClientResponse = resource
				.get(ClientResponse.class);

		// THEN
		Assert.assertEquals(actualClientResponse.getStatus(), expecteStatus);
	}

	@Test
	public void shouldAddTeam() {

		// GIVEN
		// int expecteStatus = 200;
		TeamTo team = new TeamTo();
		team.setName("akshay1111");
		// WHEN
		WebResource service = resource().path("team/add");
		ClientResponse resp = service.type(MediaType.APPLICATION_JSON).post(
				ClientResponse.class, team);
		System.out.println("Got stuff: " + resp);
		String text = resp.getEntity(String.class);
		System.out.println("Got Entity"+text);
		// THEN
		Assert.assertEquals(200, resp.getStatus());

	}

	@Test
	public void shouldDeleteTeam() {
		// GIVEN

		TeamTo team = new TeamTo();
		team.setName("akshay111");
		// WHEN

		WebResource service = resource().path("team/delete");
		ClientResponse resp = service.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_JSON)
				.delete(ClientResponse.class, team);
		System.out.println("Got stuff: " + resp);
		// THEN
		Assert.assertEquals(200, resp.getStatus());

	}

}
