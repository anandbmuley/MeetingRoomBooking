package com.merobo.services

import com.merobo.beans.UserBean
import com.merobo.builders.UserBeanBuilder
import com.merobo.exceptions.UserNotFoundException
import com.merobo.repositories.UserRepository
import spock.lang.Shared
import spock.lang.Specification

import javax.swing.text.html.Option

class UserServiceSpec extends Specification{

    @Shared
    UserService userService

    @Shared
    UserRepository mockUserRepository

    @Shared
    UserBeanBuilder userBeanBuilder

    def setup(){
        userBeanBuilder = new UserBeanBuilder()
        mockUserRepository = Mock(UserRepository)
        userService = new UserService(userRepository: mockUserRepository)
    }

    def "resetpassword - should throw an error if the user not found"(){
        given:"Username of the user to reset password"
        def username = "andie"
        1 * mockUserRepository.findByUsername(username) >> Optional.empty()

        when:"password is reset"
        userService.resetPassword(username)

        then:"exception should be thrown if user is not found"
        thrown(UserNotFoundException)
    }

    def "resetpassword - should reset the password with new password"(){
        given:"Username of the user to reset password"
        def username = "ronnie"
        UserBean bean = userBeanBuilder.buildUser()
        1 * mockUserRepository.findByUsername(username) >> {Optional.of(bean)}

        when:"password is reset"
        userService.resetPassword(username)

        then:"password should be reset successfully"
        1 * mockUserRepository.save({UserBean it->
            assert it.password == "Password123"
            true
        })
    }

}

