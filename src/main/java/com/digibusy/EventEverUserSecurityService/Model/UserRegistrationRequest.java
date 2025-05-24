package com.digibusy.EventEverUserSecurityService.Model;

import lombok.*;

public class UserRegistrationRequest {
    private String email;
    private String password;
    private String securityAnswer;
    private String customUserId; // The user-defined ID

    public UserRegistrationRequest() {
    }

    public UserRegistrationRequest(String email, String password, String securityAnswer, String customUserId) {
        this.email = email;
        this.password = password;
        this.securityAnswer = securityAnswer;
        this.customUserId = customUserId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    public String getCustomUserId() {
        return customUserId;
    }

    public void setCustomUserId(String customUserId) {
        this.customUserId = customUserId;
    }

    @Override
    public String toString() {
        return "UserRegistrationRequest{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", securityAnswer='" + securityAnswer + '\'' +
                ", customUserId='" + customUserId + '\'' +
                '}';
    }
}

