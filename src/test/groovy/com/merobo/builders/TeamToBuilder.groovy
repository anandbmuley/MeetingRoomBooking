package com.merobo.builders

import com.merobo.dtos.TeamTo
import com.merobo.dtos.UserTo

class TeamToBuilder {

    String id
    String name
    List<UserTo> memberTos

    public TeamToBuilder() {
        id = "we13-dwd2-343d-344d-das2"
        name = "Carribean"
    }

    public TeamTo build() {
        TeamTo teamTo = new TeamTo(
                id: id,
                name: name
        )
        teamTo
    }

}
