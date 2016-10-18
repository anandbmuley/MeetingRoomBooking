package com.merobo.builders

import com.merobo.beans.TeamBean

class TeamBeanBuilder {

    String name

    public TeamBeanBuilder(){
        name = "Australia"
    }

    public TeamBean build(){
        TeamBean teamBean = new TeamBean(name: name)
        teamBean
    }

}
