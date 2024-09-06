// Placeholder for UtilityController.java
package com.dpaywallet.controllers;

import com.dpaywallet.models.UtilityPayment;
import com.dpaywallet.services.UtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilities")
public class UtilityController {

    @Autowired
    private UtilityService utilityService;

    @PostMapping("/pay")
    public ResponseEntity<?> payUtilityBill(@RequestBody UtilityPayment payment) {
        UtilityPayment savedPayment = utilityService.payUtilityBill(payment);
        return ResponseEntity.ok(savedPayment);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserUtilityPayments(@PathVariable Long userId) {
        List<UtilityPayment> payments = utilityService.findAllPaymentsByUserId(userId);
        return ResponseEntity.ok(payments);
    }
}
