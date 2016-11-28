package com.merobo.services

import com.merobo.beans.UserBean
import com.merobo.builders.UserBeanBuilder
import com.merobo.builders.UserDtoBuilder
import com.merobo.dtos.UserTo
import com.merobo.exceptions.DuplicateUsernameException
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
            assert it.password == "UGFzc3dvcmQxMjM"
            true
        })
    }

    def "create - should create user with encoded password"(){
        given:"user to register"
        UserTo userTo = new UserDtoBuilder().build()
        1 * mockUserRepository.findByUsername(userTo.username) >> Optional.empty()

        when:"create method is called"
        userService.create(userTo)

        then:"user is registered successfully"
        1 * mockUserRepository.save({UserBean it->
            assert it.name == userTo.name
            assert it.password != userTo.password
            assert it.teamName == userTo.teamName
            it.id = "abcd-1234"
            true
        })
        assert userTo.id == "abcd-1234"
    }

    def "create - should throw duplicate username exception if username is already taken"(){
        given:"user to register"
        UserTo userTo = new UserDtoBuilder().build()
        UserBean bean = new UserBeanBuilder().buildUser()
        1 * mockUserRepository.findByUsername(userTo.username) >> Optional.of(bean)

        when:"create method is called"
        userService.create(userTo)

        then:"duplicate username exception is thrown"
        thrown(DuplicateUsernameException)
    }

    def "update - should update user details"(){
        given:"new password for updation"
        UserTo userTo = new UserDtoBuilder().build()
        UserBean userBean = new UserBeanBuilder().buildUser()
        1 * mockUserRepository.findByUsername(userTo.username) >> Optional.of(userBean)

        when:"update method is called"
        userService.update(userTo)

        then:"details should be updated successfully"
        1 * mockUserRepository.save({UserBean bean->
            assert bean.password != userTo.password
            true
        })
    }

    def "update - should not update details if the user is not found"(){
        given:"new password for updation"
        UserTo userTo = new UserDtoBuilder().build()
        1 * mockUserRepository.findByUsername(userTo.username) >> Optional.empty()

        when:"update method is called"
        userService.update(userTo)

        then:"user not found exception is thrown"
        thrown(UserNotFoundException)
    }

}

