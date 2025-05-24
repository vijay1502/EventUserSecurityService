package com.digibusy.EventEverUserSecurityService.Controller;

import com.digibusy.EventEverUserSecurityService.Service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private TokenService tokenService;

    // Generate Token for a User
    @GetMapping("/generate-token")
    public ResponseEntity<String> generateToken(@RequestParam String email) {
        String token = tokenService.generateToken(email);
        return ResponseEntity.ok("Verification token generated: " + token);
    }

    // Verify Token
//    @GetMapping("/verify")
//    public ResponseEntity<String> verifyToken(@RequestParam String token) {
//        try {
//            tokenService.validateToken(token);
//            return ResponseEntity.ok("Token verified successfully");
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
}

