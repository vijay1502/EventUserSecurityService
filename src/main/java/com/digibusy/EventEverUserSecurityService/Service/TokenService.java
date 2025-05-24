package com.digibusy.EventEverUserSecurityService.Service;

import com.digibusy.EventEverUserSecurityService.Model.VerifyToken;
import com.digibusy.EventEverUserSecurityService.Repository.VerifyTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TokenService {

    @Autowired
    private VerifyTokenRepository tokenRepository;

    // Generate and Save Token
    public String generateToken(String email) {
        VerifyToken token = new VerifyToken();
        token.setToken(UUID.randomUUID().toString());
        token.setEmail(email);
        token.setExpiryDate(LocalDateTime.now().plusMinutes(30)); // Token expires in 30 minutes

        tokenRepository.save(token);
        return token.getToken();
    }

    // Validate Token
    public boolean validateToken(String token) {
        VerifyToken verifyToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Token"));
        if (verifyToken.getExpiryDate() == null || LocalDateTime.now().isAfter(verifyToken.getExpiryDate())) {
            throw new IllegalArgumentException("Token expired");
        }

        return true;
    }
}

