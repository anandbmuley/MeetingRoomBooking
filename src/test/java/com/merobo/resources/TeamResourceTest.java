package com.merobo.resources;

import javax.ws.rs.core.MediaType;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.merobo.models.Team;
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
		int expecteStatus = 200;
		Team team = new Team();
		team.setName("ajay");
		// WHEN
		// Entity<Team> userEntity = Entity.entity(team,
		// MediaType.APPLICATION_JSON);
		// target("team/add").request().post(team); // Here we send POST request
		// Response response = target("users/find").queryParam("name",
		// "ajay").request().get(); //Here we send GET request for retrieving
		// results
		// Assert.assertEquals("ajay", ((Object)
		// response).readEntity(Team.class).getName());

		WebResource resource = resource().path("team/add");
		resource.type(MediaType.APPLICATION_JSON).header("Content-type",
				MediaType.APPLICATION_JSON);
		resource.accept(MediaType.APPLICATION_JSON);
		ClientResponse clientResponse = resource.post(ClientResponse.class,
				team);

		// THEN
		Assert.assertEquals(clientResponse.getStatus(), expecteStatus);
	}


}
