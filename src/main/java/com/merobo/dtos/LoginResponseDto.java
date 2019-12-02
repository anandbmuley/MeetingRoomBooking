package com.merobo.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.merobo.utils.DateConverterUtil;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginResponseDto {

    private final String loginTime;

    public LoginResponseDto() {
        loginTime = DateConverterUtil.toString(new Date(), DateConverterUtil.PATTERN_DD_MM_YYYY_HH_MM_MERIDIAN);
    }

    public String getLoginTime() {
        return loginTime;
    }
}
