package com.merobo.services;

import java.util.List;

import com.merobo.beans.TeamBean;
import com.merobo.dtos.TeamTo;

public interface TeamService {

	
	public TeamTo addTeam(TeamTo teamTo);

	public List<TeamBean> getAllTeam();

	
	public List<TeamBean> deleteTeam(TeamTo teamTo);

	public TeamTo findTeam(String name);


}
