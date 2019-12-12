package com.merobo.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.merobo.beans.UserRole;

public class RegisterUserDto {

    private String name;
    private String emailId;
    private String contactNo;
    private String username;
    private String password;
    private UserRole role;

    @JsonCreator
    public RegisterUserDto(
            String name,
            String emailId,
            String contactNo,
            String username,
            String password,
            UserRole role) {
        this.name = name;
        this.emailId = emailId;
        this.contactNo = contactNo;
        this.username = username;
        this.password = password;
        this.role = (role == null ? UserRole.USER : role);
    }

    public UserRole getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
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

}
