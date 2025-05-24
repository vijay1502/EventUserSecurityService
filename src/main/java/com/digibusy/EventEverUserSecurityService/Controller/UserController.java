package com.digibusy.EventEverUserSecurityService.Controller;

import com.digibusy.EventEverUserSecurityService.Model.UserLoginRequest;
import com.digibusy.EventEverUserSecurityService.Model.UserRegistrationRequest;
import com.digibusy.EventEverUserSecurityService.Model.Users;
import com.digibusy.EventEverUserSecurityService.Service.AuthenticationService;
import com.digibusy.EventEverUserSecurityService.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    AuthenticationService authenticationService;

    // ✅ User Registration API
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(
            @RequestBody UserRegistrationRequest registrationRequest) {

        return new ResponseEntity(userService.registerUser(registrationRequest), HttpStatus.ACCEPTED);
    }
//
    // ✅ User Authentication API
    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody UserLoginRequest loginRequest) {
        return new ResponseEntity<>(userService.loginUser(loginRequest),HttpStatus.OK);
    }
}

