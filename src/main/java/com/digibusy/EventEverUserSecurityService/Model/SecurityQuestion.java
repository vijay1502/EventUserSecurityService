package com.digibusy.EventEverUserSecurityService.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "security_questions")
public class SecurityQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email; // The email associated with the user

    @Column(nullable = false)
    private String securityAnswerHash; // Hashed security answer

    public SecurityQuestion() {
    }

    public SecurityQuestion(Long id, String email, String securityAnswerHash) {
        this.id = id;
        this.email = email;
        this.securityAnswerHash = securityAnswerHash;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSecurityAnswerHash() {
        return securityAnswerHash;
    }

    public void setSecurityAnswerHash(String securityAnswerHash) {
        this.securityAnswerHash = securityAnswerHash;
    }

    @Override
    public String toString() {
        return "SecurityQuestion{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", securityAnswerHash='" + securityAnswerHash + '\'' +
                '}';
    }
}
