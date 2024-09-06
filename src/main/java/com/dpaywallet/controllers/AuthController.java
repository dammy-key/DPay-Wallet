package com.dpaywallet.controllers;

import com.dpaywallet.models.User;
import com.dpaywallet.services.AuthService;
import com.dpaywallet.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        return userService.getUserByPhoneNumber(user.getPhoneNumber())
            .map(u -> ResponseEntity.ok(authService.generateOtp(u)))
            .orElse(ResponseEntity.badRequest().body("Invalid phone number or password"));
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyOtp(@RequestParam String phoneNumber, @RequestParam String otpCode) {
        boolean isValid = authService.verifyOtp(phoneNumber, otpCode);
        return isValid ? ResponseEntity.ok("OTP verified") : ResponseEntity.badRequest().body("Invalid OTP");
    }
}
