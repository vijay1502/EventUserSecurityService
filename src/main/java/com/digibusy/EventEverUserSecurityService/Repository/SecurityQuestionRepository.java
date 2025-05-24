package com.digibusy.EventEverUserSecurityService.Repository;

import com.digibusy.EventEverUserSecurityService.Model.SecurityQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SecurityQuestionRepository extends JpaRepository<SecurityQuestion, Long> {
    Optional<SecurityQuestion> findByEmail(String email);
}