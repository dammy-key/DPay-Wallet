package com.dpaywallet.services;

import com.dpaywallet.models.UtilityPayment;
import com.dpaywallet.models.User;
import com.dpaywallet.repositories.UtilityPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UtilityService {

    @Autowired
    private UtilityPaymentRepository utilityPaymentRepository;

    @Autowired
    private UserService userService;

    public UtilityPayment payUtility(String userPhoneNumber, String utilityType, BigDecimal amount) {
        User user = userService.getUserByPhoneNumber(userPhoneNumber)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        UtilityPayment utilityPayment = new UtilityPayment();
        utilityPayment.setUser(user);
        utilityPayment.setUtilityType(utilityType);
        utilityPayment.setAmount(amount);
        utilityPayment.setPaymentTime(LocalDateTime.now());

        return utilityPaymentRepository.save(utilityPayment);
    }

    public List<UtilityPayment> getUtilityPaymentsByUser(User user) {
        return utilityPaymentRepository.findByUser(user);
    }

    // Method to pay a utility bill directly
    public UtilityPayment payUtilityBill(UtilityPayment payment) {
        return utilityPaymentRepository.save(payment);
    }

    // Method to find all payments by user ID
    public List<UtilityPayment> findAllPaymentsByUserId(Long userId) {
        return utilityPaymentRepository.findAllByUserId(userId);
    }
}
