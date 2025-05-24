package com.digibusy.EventEverUserSecurityService.Model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String hashedPassword; // Password stored using selected encryption

    @Column(nullable = false)
    private String securityAnswer;

    private boolean isEnabled = false;
    public Users() {
    }

    public Users(String id, String email, String hashedPassword, String securityAnswer) {
        this.id = id;
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.securityAnswer = securityAnswer;
    }

    public Users(String email, String hashedPassword, String securityAnswer) {
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.securityAnswer = securityAnswer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", hashedPassword='" + hashedPassword + '\'' +
                ", securityAnswer='" + securityAnswer + '\'' +
                '}';
    }
}

