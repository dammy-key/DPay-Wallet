package com.dpaywallet.services;

import com.dpaywallet.models.Otp;
import com.dpaywallet.models.User;
import com.dpaywallet.repositories.OtpRepository;
import com.dpaywallet.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OtpRepository otpRepository;

    public User registerUser(User user) {
        // Additional logic to validate user and save to the database
        return userRepository.save(user);
    }

    public String generateOtp(String phoneNumber) {
        String otpCode = String.format("%04d", new Random().nextInt(10000));
        Otp otp = new Otp();
        otp.setOtpCode(otpCode);
        otp.setPhoneNumber(phoneNumber);
        otp.setExpiryTime(LocalDateTime.now().plusMinutes(5));
        otpRepository.save(otp);
        return otpCode;
    }

    public boolean verifyOtp(String phoneNumber, String otpCode) {
        Optional<Otp> otpOptional = otpRepository.findByOtpCodeAndPhoneNumber(otpCode, phoneNumber);
        if (otpOptional.isPresent()) {
            Otp otp = otpOptional.get();
            return otp.getExpiryTime().isAfter(LocalDateTime.now());
        }
        return false;
    }

    public Optional<User> loginUser(String phoneNumber, String password) {
        Optional<User> user = userRepository.findByPhoneNumber(phoneNumber);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return user;
        }
        return Optional.empty();
    }
}
