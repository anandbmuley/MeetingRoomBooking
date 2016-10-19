package com.merobo.builders

import com.merobo.beans.UserBean

class UserBeanBuilder {

    String id
    String username
    String password
    String name
    String teamName
    List<UserBean> users

    public UserBeanBuilder() {
        id = "UID101"
        username = "alberto"
        password = "Albert@123"
        name = "Albert"
        teamName = "Scientific Explorers"
        users = [new UserBean(
                id: id,
                username: username,
                password: password,
                name: name,
                teamName: teamName
        )]
    }

    public UserBeanBuilder withUser(UserBean userBean) {
        users.add(userBean)
        this
    }

    public List<UserBean> build() {
        users
    }
}
