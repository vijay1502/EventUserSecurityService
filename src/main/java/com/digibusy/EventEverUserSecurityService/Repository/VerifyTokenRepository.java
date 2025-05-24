package com.digibusy.EventEverUserSecurityService.Repository;

import com.digibusy.EventEverUserSecurityService.Model.VerifyToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerifyTokenRepository extends JpaRepository<VerifyToken,Long> {
Optional<VerifyToken> findByToken(String token);
}
