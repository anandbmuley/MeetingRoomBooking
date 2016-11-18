package com.merobo.services

import com.merobo.beans.TeamBean
import com.merobo.beans.UserBean
import com.merobo.builders.TeamBeanBuilder
import com.merobo.builders.TeamToBuilder
import com.merobo.builders.UserBeanBuilder
import com.merobo.dtos.TeamTo
import com.merobo.repositories.TeamRepository
import com.merobo.repositories.UserRepository
import spock.lang.Specification

class TeamServiceTest extends Specification {

    TeamService teamService
    TeamRepository mockTeamRepository
    UserRepository mockUserRepository

    def setup() {
        mockTeamRepository = Mock(TeamRepository)
        mockUserRepository = Mock(UserRepository)
        teamService = new TeamService(
                teamRepository: mockTeamRepository,
                userRepository: mockUserRepository
        )
    }

    def "updateTeam - should update TeamName"() {
        given:"a teamTo with teamName"
        TeamTo teamTo = new TeamToBuilder().build()
        TeamBean teamBean = new TeamBeanBuilder().build()
        List<UserBean> users = new UserBeanBuilder().withUser(new UserBean(id: "UID201")).build()

        when:"team details are updated"
        teamService.updateTeam(teamTo)

        then:"details are updated successfully"
        1 * mockTeamRepository.findOne(teamTo.id) >> {teamBean}
        1 * mockTeamRepository.save(teamBean)
        1 * mockUserRepository.findByTeamName(teamBean.name) >> {users}
        1 * mockUserRepository.save(users)
        0 * _
    }

}
