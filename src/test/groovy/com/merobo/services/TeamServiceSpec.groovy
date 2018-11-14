package com.merobo.services

import com.merobo.beans.TeamBean
import com.merobo.beans.UserBean
import com.merobo.builders.TeamBeanBuilder
import com.merobo.builders.TeamToBuilder
import com.merobo.builders.UserBeanBuilder
import com.merobo.common.AssertionsCatalogue
import com.merobo.common.SharedSpecification
import com.merobo.dtos.TeamTo
import com.merobo.exceptions.NoDataFoundException
import com.merobo.repositories.TeamRepository
import com.merobo.repositories.UserRepository

class TeamServiceSpec extends SharedSpecification {

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
        given: "a teamTo with teamName"
        TeamTo teamTo = new TeamToBuilder().build()
        TeamBean teamBean = new TeamBeanBuilder().build()
        List<UserBean> users = new UserBeanBuilder().withUser(new UserBean(id: "UID201")).build()

        when: "team details are updated"
        teamService.updateTeam(teamTo)

        then: "details are updated successfully"
        1 * mockTeamRepository.findOne(teamTo.id) >> { teamBean }
        1 * mockTeamRepository.save(teamBean)
        1 * mockUserRepository.findByTeamName(teamBean.name) >> { users }
        1 * mockUserRepository.save(users)
    }

    def "getAllTeams - should get all teams with team members"() {
        given: "users are registered with associated teams"
        List<UserBean> users = new UserBeanBuilder().build()
        List<TeamBean> teams = new TeamBeanBuilder().withTeam(new TeamBean(
                id: "abcd-123",
                name: "Scientific Explorers"
        )).buildTeams()

        1 * mockUserRepository.findAll() >> { users }
        1 * mockTeamRepository.findByNameIn({ names ->
            assert names.size == 1
            assert names[0] == "Scientific Explorers"
            true
        }) >> { teams }

        when: "getAllTeams is called"
        List<TeamTo> actualTeams = teamService.getAllTeams()

        then: "teams with member details are fetched"
        AssertionsCatalogue.assertTeams(actualTeams)

    }

    def "getAllTeams - should not return teams details"() {
        given: "users are registered without associated teams"
        List<UserBean> users = new UserBeanBuilder().build()
        List<TeamBean> teams = []

        1 * mockUserRepository.findAll() >> { users }
        1 * mockTeamRepository.findByNameIn({ names ->
            assert names.size == 1
            assert names[0] == "Scientific Explorers"
            true
        }) >> { teams }

        when: "getAllTeams is called"
        List<TeamTo> actualTeams = teamService.getAllTeams()

        then: "teams with member details are fetched"
        assert actualTeams.size() == 0

    }

    def "getTeamList - should throw no data found exception if no records found"() {
        given: "none of the teams are created yet"
        1 * mockTeamRepository.findAll() >> { [] }

        when: "getTeamList is called"
        teamService.getTeamList()

        then: "NoDataFoundException is thrown"
        thrown(NoDataFoundException)
    }

    def "getTeamList - should throw no data found exception if no records found null"() {
        given: "none of the teams are created yet"
        1 * mockTeamRepository.findAll() >> { null }

        when: "getTeamList is called"
        teamService.getTeamList()

        then: "NoDataFoundException is thrown"
        thrown(NoDataFoundException)
    }

    def "getTeamList - should create a team list"() {
        given: "teams are created in the system"
        def teams = new TeamBeanBuilder().withTeam(new TeamBean(id: "TID101", name: "Rangers")).buildTeams()
        1 * mockTeamRepository.findAll() >> { teams }

        when: "getTeamList is called"
        List<TeamTo> actual = teamService.getTeamList()

        then: "NoDataFoundException is thrown"
        assert actual.size() == 1
        assert actual[0].id == "TID101"
        assert actual[0].name == "Rangers"
    }

    def "getAll - should not return teams"() {
        given: "no teams are added yet"
        1 * mockTeamRepository.findAll() >> teamsData

        when: "teams are fetched"
        List<TeamTo> teams = teamService.getAll()

        then: "empty team list is sent"
        assert teams.size() == 0

        where: "teams are not present"
        teamsData | _
        []        | _
        null      | _
    }

    def "getAll - should return all teams"() {
        given: "some teams are added"
        TeamBean expectedTeam = new TeamBean(
                id: UUID.randomUUID().toString(),
                name: "Scientific Explorers"
        )
        1 * mockTeamRepository.findAll() >> new TeamBeanBuilder().withTeam(expectedTeam).buildTeams()

        when: "teams are fetched"
        List<TeamTo> actualTos = teamService.getAll()

        then: "empty team list is sent"
        assert actualTos.size() == 1
        TeamTo actualTeamTo = actualTos[0]
        assert actualTeamTo.id == expectedTeam.id
        assert actualTeamTo.name == expectedTeam.name

    }

}
