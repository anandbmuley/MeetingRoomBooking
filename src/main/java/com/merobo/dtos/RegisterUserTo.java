package com.merobo.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterUserTo {

    private String name;
    private String username;
    private String password;
    private String teamName;

    @JsonCreator
    public RegisterUserTo(
            @JsonProperty("name") String name,
            @JsonProperty("username") String username,
            @JsonProperty("password") String password,
            @JsonProperty("teamName") String teamName) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.teamName = teamName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getName() {
        return name;
    }
}
