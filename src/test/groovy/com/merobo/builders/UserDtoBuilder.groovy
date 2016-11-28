package com.merobo.builders

import com.merobo.dtos.UserTo

class UserDtoBuilder {
    private String id;
    private String username;
    private String password;
    private String name;
    private String teamName;
    // Timeout for cookies
    private long cookieTimeout = 15;
    private String loginTime;
    private String adminPasscode;
    private String adminToken;

    public UserDtoBuilder(){
        username = "rocky"
        password = "Rocky@123"
        name = "Rocky"
        teamName = "WWE"
    }

    public UserTo build(){
        UserTo userTo = new UserTo(
                username: username,
                password: password,
                name: name,
                teamName: teamName
        )
        userTo
    }

}
