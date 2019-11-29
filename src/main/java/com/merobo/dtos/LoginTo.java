package com.merobo.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginTo {

    private String username;
    private String password;

    @JsonCreator
    public LoginTo(
            @JsonProperty String username,
            @JsonProperty String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "LoginTo{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
