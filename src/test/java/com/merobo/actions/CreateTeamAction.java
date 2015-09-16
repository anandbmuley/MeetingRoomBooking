package com.merobo.actions;

import org.hamcrest.Description;
import org.jmock.api.Action;
import org.jmock.api.Invocation;

import com.merobo.beans.TeamBean;

public class CreateTeamAction implements Action {

	@Override
	public void describeTo(Description description) {
		description.appendText("Creating Team");
	}

	@Override
	public Object invoke(Invocation invocation) throws Throwable {
		TeamBean teamBean = (TeamBean) invocation.getParameter(0);
		teamBean.setId("55efe101e4b03091c264fcd3");
		return teamBean;
	}

}
