package com.merobo.dtos;

public class LoginResponseTo {

    private String id;
    private String name;
    private String teamName;
    private long cookieTimeout = 15;

    public LoginResponseTo(String id, String name, String teamName) {
        this.id = id;
        this.name = name;
        this.teamName = teamName;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTeamName() {
        return teamName;
    }

    public long getCookieTimeout() {
        return cookieTimeout;
    }
}
