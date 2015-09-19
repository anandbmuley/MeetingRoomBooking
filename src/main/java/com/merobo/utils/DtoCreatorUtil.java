package com.merobo.utils;

import java.util.ArrayList;
import java.util.List;

import com.merobo.beans.MemberBean;
import com.merobo.beans.TeamBean;
import com.merobo.dtos.MemberTo;
import com.merobo.dtos.TeamTo;

public abstract class DtoCreatorUtil {

	public static List<TeamTo> createTeamTos(List<TeamBean> teams) {
		List<TeamTo> teamTos = new ArrayList<TeamTo>();
		for (TeamBean teamBean : teams) {
			teamTos.add(createTeamTo(teamBean));
		}
		return teamTos;
	}

	public static TeamTo createTeamTo(TeamBean teamBean) {
		TeamTo teamTo = new TeamTo();
		teamTo.setId(teamBean.getId());
		teamTo.setName(teamBean.getName());
		for (MemberBean memberBean : teamBean.getMemberBeans()) {
			MemberTo memberTo = new MemberTo();
			memberTo.setName(memberBean.getName());
			teamTo.getMemberTos().add(memberTo);
		}
		return teamTo;
	}

}
