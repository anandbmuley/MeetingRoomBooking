package com.merobo.utils

import com.merobo.beans.BookingBean
import com.merobo.beans.TeamBean
import com.merobo.beans.UserBean
import com.merobo.builders.BookingBeanBuilder
import com.merobo.builders.TeamBeanBuilder
import com.merobo.builders.UserBeanBuilder
import com.merobo.common.AssertionsCatalogue
import com.merobo.dtos.BookingTo
import com.merobo.dtos.TeamTo
import com.merobo.dtos.UserTo
import spock.lang.Specification

class DtoCreatorUtilSpec extends Specification{

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

    def "createUserTo - should create user to"(){
        given:"userbean to convert to userdto"
        UserBean userBean = new UserBeanBuilder().buildUser()

        when:"createUserTo is called"
        UserTo actual = DtoCreatorUtil.createUserTo(userBean)

        then:"userto is created with required details populated"
        AssertionsCatalogue.assertCreateUserTo(actual)
    }

    def "createBookingTos - should create booking to with given details"(){
        given:"list of bookings for the day"
        List<BookingBean> bookings = new BookingBeanBuilder().buildBookings()

        when:"createBookingTos is called"
        List<BookingTo> actual = DtoCreatorUtil.createBookingTos(bookings)

        then:"bookings are populated"
        AssertionsCatalogue.assertBookingsTo(actual)
    }

}
