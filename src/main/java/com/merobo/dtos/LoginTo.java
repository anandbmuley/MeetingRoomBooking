package com.merobo.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.merobo.utils.DateConverterUtil;

import java.util.Date;

public class LoginTo {


    private String username;
    private String password;
    private String loginTime;

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

    public void sucessfulLogin() {
        password = "";
        loginTime = DateConverterUtil.toString(new Date(), DateConverterUtil.PATTERN_HH_MM_MERIDIAN);
    }

}
