package com.merobo.dtos;

import abm.authenticator.domain.UserRole;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    private String id;
    private String username;
    private String password;
    private String name;
    private String emailId;
    private String contactNo;
    private UserRole  role;

    public UserDto() {
    }

    public UserDto(String name, String emailId, String contactNo, UserRole role) {
        this.name = name;
        this.emailId = emailId;
        this.contactNo = contactNo;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getContactNo() {
        return contactNo;
    }

    public UserRole getRole() {
        return role;
    }
}
