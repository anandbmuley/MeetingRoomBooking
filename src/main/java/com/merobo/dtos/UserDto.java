package com.merobo.dtos;

public class UserDto {

    // Timeout for cookies
    private String id;
    private String teamName;
    private String username;
    private String password;
    private String name;
    private String adminPasscode;
    private String adminToken;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
