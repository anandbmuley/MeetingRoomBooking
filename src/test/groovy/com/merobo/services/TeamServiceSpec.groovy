package com.merobo.services

import com.merobo.beans.TeamBean
import com.merobo.beans.UserBean
import com.merobo.builders.TeamBeanBuilder
import com.merobo.builders.TeamToBuilder
import com.merobo.builders.UserBeanBuilder
import com.merobo.common.AssertionsCatalogue
import com.merobo.dtos.TeamTo
import com.merobo.repositories.TeamRepository
import com.merobo.repositories.UserRepository
import spock.lang.Specification

class TeamServiceSpec extends Specification {

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

    def "getAllTeams - should get all teams with team members"(){
        given:"users are registered with associated teams"
        List<UserBean> users = new UserBeanBuilder().build()
        List<TeamBean> teams = new TeamBeanBuilder().withTeam(new TeamBean(
                id: "abcd-123",
                name: "Scientific Explorers"
        )).buildTeams()

        1 * mockUserRepository.findAll() >> {users}
        1 * mockTeamRepository.findByNameIn({names ->
            assert names.size == 1
            assert names[0] == "Scientific Explorers"
            true
        }) >> {teams}
        0 * _

        when:"getAllTeams is called"
        List<TeamTo> actualTeams = teamService.getAllTeams()

        then:"teams with member details are fetched"
        AssertionsCatalogue.assertTeams(actualTeams)

    }

    def "getAllTeams - should not return teams details"(){
        given:"users are registered without associated teams"
        List<UserBean> users = new UserBeanBuilder().build()
        List<TeamBean> teams = []

        1 * mockUserRepository.findAll() >> {users}
        1 * mockTeamRepository.findByNameIn({names ->
            assert names.size == 1
            assert names[0] == "Scientific Explorers"
            true
        }) >> {teams}
        0 * _

        when:"getAllTeams is called"
        List<TeamTo> actualTeams = teamService.getAllTeams()

        then:"teams with member details are fetched"
        assert actualTeams.size() == 0

    }

}
