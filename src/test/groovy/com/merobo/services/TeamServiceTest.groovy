package com.merobo.services

import com.merobo.beans.TeamBean
import com.merobo.beans.UserBean
import com.merobo.builders.TeamBeanBuilder
import com.merobo.builders.TeamToBuilder
import com.merobo.builders.UserBeanBuilder
import com.merobo.common.RootTestConfig
import com.merobo.dtos.TeamTo
import com.merobo.repositories.TeamRepository
import com.merobo.repositories.UserRepository
import org.jmock.Expectations
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test

class TeamServiceTest extends RootTestConfig {

    TeamService teamService
    TeamRepository mockTeamRepository
    UserRepository mockUserRepository

    @BeforeClass
    public void setUp() {
        mockTeamRepository = context.mock(TeamRepository)
        mockUserRepository = context.mock(UserRepository)
        teamService = new TeamService(
                teamRepository: mockTeamRepository,
                userRepository: mockUserRepository
        )
    }

    @Test
    public void ShouldUpdateTeamName() {
        // GIVEN
        TeamTo teamTo = new TeamToBuilder().build()
        TeamBean teamBean = new TeamBeanBuilder().build()
        List<UserBean> users = new UserBeanBuilder().withUser(new UserBean(id: "UID201")).build()

        context.checking(new Expectations() {
            {
                oneOf(mockTeamRepository).findOne(teamTo.id)
                will(returnValue(teamBean))
                oneOf(mockTeamRepository).save(teamBean)
                oneOf(mockUserRepository).findByTeamName(teamBean.name)
                will(returnValue(users))
                oneOf(mockUserRepository).save(users)
            }
        })

        // WHEN
        teamService.updateTeam(teamTo)

        // THEN
    }

}
