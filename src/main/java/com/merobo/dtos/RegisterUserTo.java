package com.merobo.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;

public class RegisterUserTo {

    private String name;
    private String emailId;
    private String contactNo;
    private String username;
    private String password;
    private String teamName;

    @JsonCreator
    public RegisterUserTo(
            String name,
            String emailId,
            String contactNo,
            String username,
            String password) {
        this.name = name;
        this.emailId = emailId;
        this.contactNo = contactNo;
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

    public String getEmailId() {
        return emailId;
    }

    public String getContactNo() {
        return contactNo;
    }

    @Override
    public String toString() {
        return "RegisterUserTo{" +
                "name='" + name + '\'' +
                ", emailId='" + emailId + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", teamName='" + teamName + '\'' +
                '}';
    }
}
