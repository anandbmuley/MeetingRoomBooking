package com.merobo.services;

import java.util.List;

import com.merobo.dtos.TeamTo;

public interface TeamService {

	TeamTo addTeam(TeamTo teamTo);

	List<TeamTo> getAllTeams();

	void deleteTeam(TeamTo teamTo);

	TeamTo findTeam(String name);

	TeamTo addMember(String teamId, String memberName);

}
