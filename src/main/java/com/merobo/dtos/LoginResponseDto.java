package com.merobo.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.merobo.utils.DateConverterUtil;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginResponseDto {

    private final String userId;
    private final String loginTime;

    public LoginResponseDto(String userId) {
        loginTime = DateConverterUtil.toString(new Date(), DateConverterUtil.PATTERN_DD_MM_YYYY_HH_MM_MERIDIAN);
        this.userId = userId;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public String getUserId() {
        return userId;
    }
}
