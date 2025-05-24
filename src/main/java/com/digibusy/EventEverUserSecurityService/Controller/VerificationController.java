package com.digibusy.EventEverUserSecurityService.Controller;

import com.digibusy.EventEverUserSecurityService.Model.Users;
import com.digibusy.EventEverUserSecurityService.Model.VerifyToken;
import com.digibusy.EventEverUserSecurityService.Repository.UserRepository;
import com.digibusy.EventEverUserSecurityService.Repository.VerifyTokenRepository;
import com.digibusy.EventEverUserSecurityService.Service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class VerificationController {
    @Autowired
    private VerifyTokenRepository verificationTokenRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    TokenService tokenService;
    @GetMapping("/verify")
    public ResponseEntity<String> verifyUser(@RequestParam(value = "token") String token) {

        boolean isValid = tokenService.validateToken(token);
        if (isValid) {
            String email = verificationTokenRepository.findByToken(token).get().getEmail();
            System.out.println(email);
            Optional<Users> userOpt = userRepository.findByEmail(email);
            if (userOpt.isPresent()) {
                Users user = userOpt.get();
                user.setEnabled(true);
                userRepository.save(user);
                return ResponseEntity.ok("Email verified successfully.");
            }
        }
        return new ResponseEntity<>("Invalid or expired token.",HttpStatus.BAD_REQUEST);
    }
}

