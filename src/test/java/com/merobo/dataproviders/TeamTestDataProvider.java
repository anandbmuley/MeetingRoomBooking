package com.merobo.dataproviders;

import java.util.ArrayList;
import java.util.List;

import com.merobo.beans.TeamBean;
import com.merobo.dtos.TeamTo;

public abstract class TeamTestDataProvider {

	public static TeamTo createTeamTo() {
		TeamTo teamTo = new TeamTo();
		teamTo.setName("Maveric");
		return teamTo;
	}

	public static TeamBean createTeamBean() {
		TeamBean teamBean = new TeamBean();
		teamBean.setId("55efe101e4b03091c264fcd3");
		teamBean.setName("Maveric");
		return teamBean;
	}

	public static List<TeamBean> getAllTeams() {
		List<TeamBean> allTeams = new ArrayList<TeamBean>();
		for (int i = 0; i < 2; i++) {
			TeamBean team = new TeamBean();
			team.setId("55efe101e4b03091c264fcd" + i);
			team.setName("Maveric" + i);
			allTeams.add(team);
		}
		return allTeams;
	}

}
