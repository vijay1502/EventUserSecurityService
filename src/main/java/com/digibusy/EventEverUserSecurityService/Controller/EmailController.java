package com.digibusy.EventEverUserSecurityService.Controller;

import com.digibusy.EventEverUserSecurityService.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/send-email")
    public String sendEmail(@RequestParam String email) {
        String subject = "Your Verification Link";
        String link = "https://localhost:8084/api/auth/verify?email=" + email;
        emailService.sendEmailWithLink(email, subject, link);
        return "Email sent successfully to " + email;
    }
}

