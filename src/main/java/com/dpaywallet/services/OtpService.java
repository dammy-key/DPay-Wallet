// Placeholder for OtpService.java
package com.dpaywallet.services;

import com.dpaywallet.models.Otp;
import com.dpaywallet.repositories.OtpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class OtpService {

    @Autowired
    private OtpRepository otpRepository;

    // Generate and save an OTP for a specific phone number
    public String generateOtp(String phoneNumber) {
        String otpCode = String.format("%04d", new Random().nextInt(10000));
        Otp otp = new Otp();
        otp.setOtpCode(otpCode);
        otp.setPhoneNumber(phoneNumber);
        otp.setExpiryTime(LocalDateTime.now().plusMinutes(5)); // OTP valid for 5 minutes
        otpRepository.save(otp);
        return otpCode;
    }

    // Validate OTP for a specific phone number
    public boolean validateOtp(String phoneNumber, String otpCode) {
        Optional<Otp> otpOptional = otpRepository.findByOtpCodeAndPhoneNumber(otpCode, phoneNumber);
        if (otpOptional.isPresent()) {
            Otp otp = otpOptional.get();
            // Check if the OTP is not expired
            if (otp.getExpiryTime().isAfter(LocalDateTime.now())) {
                return true;
            }
        }
        return false;
    }

    // Method to delete an OTP after successful validation or expiration
    public void deleteOtp(Otp otp) {
        otpRepository.delete(otp);
    }

    // Optionally, you could implement a method to resend an OTP if needed
    public String resendOtp(String phoneNumber) {
        return generateOtp(phoneNumber);
    }
}
