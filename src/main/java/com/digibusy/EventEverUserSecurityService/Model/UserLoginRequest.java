package com.digibusy.EventEverUserSecurityService.Model;


public class UserLoginRequest {
    private String email;
    private String password;
    public UserLoginRequest() {
    }

    public UserLoginRequest(String email, String password, String securityAnswer) {
        this.email = email;
        this.password = password;
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


    @Override
    public String toString() {
        return "UserLoginRequest{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

