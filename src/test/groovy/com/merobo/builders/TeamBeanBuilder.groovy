package com.merobo.builders

import com.merobo.beans.TeamBean
import com.merobo.beans.UserBean

class TeamBeanBuilder {

    String id
    String name
    List<UserBean> members

    public TeamBeanBuilder() {
        id = "dsa3-dsa3-gsa2-r3ca"
        name = "Australia"
    }

    public TeamBeanBuilder withMembers() {
        members = [
                new UserBean(
                        id: "da2f-3fa2-dai2-da2r",
                        username: "rockstar",
                        password: "Rocky@123",
                        name: "Rocky",
                        teamName: "Rock Solid"
                )
        ]
        this
    }

    public TeamBean build() {
        TeamBean teamBean = new TeamBean(
                id: id,
                name: name,
                members: members
        )
        teamBean
    }

}
