package com.merobo.utils;

import java.util.ArrayList;
import java.util.List;

import com.merobo.beans.TeamBean;
import com.merobo.beans.UserBean;
import com.merobo.dtos.TeamTo;
import com.merobo.dtos.UserTo;

public abstract class DtoCreatorUtil {

	public static List<TeamTo> createTeamTos(List<TeamBean> teams) {
		List<TeamTo> teamTos = new ArrayList<TeamTo>();
		for (TeamBean teamBean : teams) {
			TeamTo teamTo = new TeamTo();
			teamTo.setId(teamBean.getId());
			teamTo.setName(teamBean.getName());
			teamTos.add(teamTo);
		}
		return teamTos;
	}

	public static List<UserTo> createUserTos(List<UserBean> users) {
		List<UserTo> userTos = new ArrayList<UserTo>();
		for (UserBean userBean : users) {
			UserTo userTo = new UserTo();
			userTo.setId(userBean.getId());
			userTo.setName(userBean.getName());
			userTo.setTeamName(userBean.getTeamName());
			userTo.setUsername(userBean.getUsername());
			userTos.add(userTo);
		}
		return userTos;
	}

}
