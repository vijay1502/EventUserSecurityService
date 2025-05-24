package com.digibusy.EventEverUserSecurityService.Service;


import com.digibusy.EventEverUserSecurityService.Model.*;
import com.digibusy.EventEverUserSecurityService.Repository.UserRepository;
import com.digibusy.EventEverUserSecurityService.Repository.VerifyTokenRepository;
import com.digibusy.EventEverUserSecurityService.Utils.RotationalShift;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.MimeMessageHelper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    VerifyTokenRepository verifyTokenRepository;

    @Autowired
    EmailService emailService;

    @Autowired
    private JavaMailSender mailSender;
    @Transactional
    public String registerUser(UserRegistrationRequest registrationRequest){
        Optional<Users> users = userRepository.findByEmail(registrationRequest.getEmail());
        if(users.isPresent()){
            System.out.println(users.get());
            if(!users.get().isEnabled()){
                return "User Verification Pending";
            }
            return "User Already Exists, Try Logging in!";
        }
        String email = registrationRequest.getEmail();
        String password = registrationRequest.getPassword();
        String securityAnswer = registrationRequest.getSecurityAnswer();
        try{
            String generateRotationalSKey = RotationalShift.generateRotationalShiftKey(securityAnswer,50);
            String hashKey = authenticationService.generateHMACSHA256(password, generateRotationalSKey);
            Users usersReg= new Users();
            usersReg.setHashedPassword(hashKey);
            usersReg.setEmail(email);
            usersReg.setSecurityAnswer(securityAnswer);
            usersReg.setEnabled(false);
            userRepository.save(usersReg);
            String token = generateAndSendVerificationToken(email);
//            String link = "http://localhost:8084/api/auth/verify?email=" + email;
//            emailService.sendEmailWithLink(email,"Event Ever Registration Check",link);

        }catch (Exception ex){
            return "Error:"+ex.getMessage();
        }
        return "Successfully registered";

    }

    private String generateAndSendVerificationToken(String email) {
        VerifyToken token = new VerifyToken();
        token.setToken(UUID.randomUUID().toString());
        token.setEmail(email);
        token.setExpiryDate(LocalDateTime.now().plusMinutes(30)); // 30 minutes expiry

        verifyTokenRepository.save(token);

        sendVerificationEmail(email, token.getToken());
        return token.getToken();
    }

    private void sendVerificationEmail(String email, String token) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(email);
            helper.setSubject("Verify Your Email");
            helper.setText("<h3>Welcome!</h3>" +
                            "<p>Please click the link below to verify your email:</p>" +
                            "<a href=\"http://localhost:8084/api/auth/verify?token=" + token + "\">Verify Now</a>",
                    true);

            mailSender.send(message);
        }  catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to send verification email");
        }
    }

    public String loginUser(UserLoginRequest loginRequest) {
        Optional<Users> users = userRepository.findByEmail(loginRequest.getEmail());
        if(users.isEmpty()){
            return "User is not present, please register";
        }
        if(users.get().isEnabled()){
        String hashedPassword = users.get().getHashedPassword();
        String password = loginRequest.getPassword();
        String securityKey = users.get().getSecurityAnswer();
        String generateRotationalSKey = RotationalShift.generateRotationalShiftKey(securityKey,50);
        boolean verificationStatus = AuthenticationService.verifyHash(password, generateRotationalSKey, hashedPassword);
        if(verificationStatus){
            return "Login Successful";
        }else {
            return "Invalid Username or password";
        }
    }else {
            return "Mail Not verified yet, please verify!";
        }
    }
}

