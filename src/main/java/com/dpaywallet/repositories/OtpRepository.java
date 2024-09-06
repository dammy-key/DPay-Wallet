// Placeholder for OtpRepository.java
package com.dpaywallet.repositories;

import com.dpaywallet.models.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OtpRepository extends JpaRepository<Otp, Long> {
    Optional<Otp> findByPhoneNumber(String phoneNumber);
    Optional<Otp> findByOtpCodeAndPhoneNumber(String otpCode, String phoneNumber);
}
