package com.merobo.beans;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {

    @Id
    private String id;
    private String name;
    private String emailId;
    private String contactNo;
    private String username;
    private String password;
    private String teamName;
    private UserRole role;

    private User() {

    }

    public User(String name, String emailId, String contactNo, String username, String password, UserRole role) {
        this.name = name;
        this.emailId = emailId;
        this.contactNo = contactNo;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getId() {
        return id;
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

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getTeamName() {
        return teamName;
    }

    public UserRole getRole() {
        return role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    @Override
    public String toString() {
        return "UserBean [id=" + id + ", username=" + username + ", password="
                + password + ", name=" + name + ", teamName=" + teamName + "]";
    }
}
