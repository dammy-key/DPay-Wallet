package com.dpaywallet.services;

import com.dpaywallet.models.Transaction;
import com.dpaywallet.models.User;
import com.dpaywallet.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserService userService;

    public Transaction sendMoney(String senderPhoneNumber, String receiverPhoneNumber, BigDecimal amount) {
        User sender = userService.getUserByPhoneNumber(senderPhoneNumber)
                .orElseThrow(() -> new IllegalArgumentException("Sender not found"));
        User receiver = userService.getUserByPhoneNumber(receiverPhoneNumber)
                .orElseThrow(() -> new IllegalArgumentException("Receiver not found"));

        Transaction transaction = new Transaction();
        transaction.setSender(sender);
        transaction.setReceiver(receiver);
        transaction.setAmount(amount);
        transaction.setTransactionTime(LocalDateTime.now());
        transaction.setTransactionType("TRANSFER");

        // Deduct amount from sender's balance
        sender.setBalance(sender.getBalance().subtract(amount));

        // Add amount to receiver's balance
        receiver.setBalance(receiver.getBalance().add(amount));

        // Save updated users
        userService.updateUser(sender);
        userService.updateUser(receiver);

        // Save the transaction
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getUserTransactions(String phoneNumber) {
        User user = userService.getUserByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return transactionRepository.findBySenderOrReceiver(user, user);
    }

    // Method to create a transaction directly
    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    // Method to find all transactions by user ID
    public List<Transaction> findAllTransactionsByUserId(Long userId) {
        return transactionRepository.findAllByUserId(userId);
    }
}
