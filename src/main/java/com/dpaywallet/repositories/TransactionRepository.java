package com.dpaywallet.repositories;

import com.dpaywallet.models.Transaction;
import com.dpaywallet.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findBySender(User sender);
    List<Transaction> findByReceiver(User receiver);
    List<Transaction> findBySenderOrReceiver(User sender, User receiver);
    List<Transaction> findAllByUserId(Long userId);
}
