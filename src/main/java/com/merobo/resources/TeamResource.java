package com.merobo.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.testng.internal.junit.ArrayAsserts;

@Path("team")
public class TeamResource {

	@GET
	@Path("add")
	public String addTeam(@QueryParam("name")String name){
		return "Team Added";
	}
	
	@GET
	@Path("list")
	public List<String> getAllTeam(){
		List<String> l1= new ArrayList<String>();
		l1.add("Team1");
		l1.add("team2");
		l1.add("team3");
		System.out.println(l1);
		return l1;
		
	}
	
	@GET
	@Path("delete")
	public String deleteTeam(){
		 return "teamDeleted";
		 
	}
	
	
	
	
}
