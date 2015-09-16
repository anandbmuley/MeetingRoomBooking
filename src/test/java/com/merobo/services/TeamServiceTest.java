package com.merobo.services;

import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.springframework.test.util.ReflectionTestUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.merobo.actions.CreateTeamAction;
import com.merobo.beans.TeamBean;
import com.merobo.dataproviders.TeamTestDataProvider;
import com.merobo.dtos.TeamTo;
import com.merobo.repositories.TeamRepository;

public class TeamServiceTest {

	private Mockery context;
	private TeamRepository mockTeamRepository;
	private TeamServiceImpl teamService;

	@BeforeClass
	public void setUp() {
		context = new Mockery();
		teamService = new TeamServiceImpl();
		mockTeamRepository = context.mock(TeamRepository.class);
		ReflectionTestUtils.setField(teamService, "teamRepository",
				mockTeamRepository);
	}

	@Test
	public void shouldAddTeam() {
		// GIVEN
		final TeamTo teamTo = TeamTestDataProvider.createTeamTo();
		final CreateTeamAction createTeamAction = new CreateTeamAction();

		context.checking(new Expectations() {
			{
				oneOf(mockTeamRepository).save(with(any(TeamBean.class)));
				will(createTeamAction);
			}
		});
		// WHEN
		teamService.addTeam(teamTo);

		// THEN
		Assert.assertEquals(teamTo.getId(), "55efe101e4b03091c264fcd3");
	}

	@Test
	public void shouldGetAllTeams() {
		// GIVEN
		final List<TeamBean> teams = TeamTestDataProvider.getAllTeams();

		context.checking(new Expectations() {
			{
				oneOf(mockTeamRepository).findAll();
				will(returnValue(teams));
			}
		});
		// WHEN
		List<TeamTo> actual = teamService.getAllTeams();

		// THEN
		Assert.assertEquals(actual.size(), 2);
	}

	@Test
	public void shouldDeleteTeam() {
		// GIVEN
		final TeamTo teamTo = new TeamTo();
		final String teamName = "Maveric";
		teamTo.setName(teamName);

		context.checking(new Expectations() {
			{
				oneOf(mockTeamRepository).deleteByName(with(teamName));
			}
		});
		// WHEN
		teamService.deleteTeam(teamTo);

		// THEN
		// Should delete team
	}

	@Test
	public void shouldFindTeamByName() {
		// GIVEN
		final String teamName = "Maveric";
		final TeamBean teamBean = TeamTestDataProvider.createTeamBean();

		context.checking(new Expectations() {
			{
				oneOf(mockTeamRepository).findByName(with(teamName));
				will(returnValue(teamBean));
			}
		});
		// WHEN
		TeamTo actual = teamService.findTeam(teamName);

		// THEN
		Assert.assertEquals(actual.getName(), teamName);
	}

}
