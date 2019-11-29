package com.merobo.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.merobo.utils.DateConverterUtil;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginResponseTo {

    private final String loginTime;

    public LoginResponseTo() {
        loginTime = DateConverterUtil.toString(new Date(), DateConverterUtil.PATTERN_HH_MM_MERIDIAN);
    }

    public String getLoginTime() {
        return loginTime;
    }
}
