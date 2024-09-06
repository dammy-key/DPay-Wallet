// Placeholder for TransactionController.java
package com.dpaywallet.controllers;

import com.dpaywallet.models.Transaction;
import com.dpaywallet.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(@RequestBody Transaction transaction) {
        Transaction savedTransaction = transactionService.createTransaction(transaction);
        return ResponseEntity.ok(savedTransaction);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserTransactions(@PathVariable Long userId) {
        List<Transaction> transactions = transactionService.findAllTransactionsByUserId(userId);
        return ResponseEntity.ok(transactions);
    }
}
