package com.merobo.utils

import com.merobo.beans.TeamBean
import com.merobo.builders.TeamBeanBuilder
import com.merobo.common.AssertionsCatalogue
import com.merobo.dtos.TeamTo
import spock.lang.Specification

class DtoCreatorUtilTest extends Specification{

    def "createTeamTo - should create TeamTo with members"() {
        given:"a team bean with all details"
        TeamBean teamBean = new TeamBeanBuilder().withMembers().build()

        when:"createTeamTo method is called"
        TeamTo actual = DtoCreatorUtil.createTeamTo(teamBean)

        then:"teamTo is created with all details"
        AssertionsCatalogue.assertTeamTo(actual)
        assert actual.memberTos.size() == 1
        actual.memberTos.forEach({ user -> AssertionsCatalogue.assertUserTo(user) })
    }

    def "createTeamTo - should create TeamTo without members"() {
        given:"a team bean without member details"
        TeamBean teamBean = new TeamBeanBuilder().build()

        when:"createTeamTo method is called"
        TeamTo actual = DtoCreatorUtil.createTeamTo(teamBean)

        then:"teamTo is populated without member details"
        AssertionsCatalogue.assertTeamTo(actual)
        assert actual.memberTos.size() == 0
    }

}
