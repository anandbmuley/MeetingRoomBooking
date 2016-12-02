package com.merobo.dtos;

public class PasswordDto {

    private String newPassword;

    public PasswordDto() {
    }

    public PasswordDto(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public String toString() {
        return "PasswordDto{" +
                "newPassword='" + newPassword + '\'' +
                '}';
    }
}
