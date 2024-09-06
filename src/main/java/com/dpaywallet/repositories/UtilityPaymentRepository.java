// Placeholder for UtilityPaymentRepository.java
package com.dpaywallet.repositories;

import com.dpaywallet.models.UtilityPayment;
import com.dpaywallet.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtilityPaymentRepository extends JpaRepository<UtilityPayment, Long> {
    List<UtilityPayment> findByUser(User user);
    List<UtilityPayment> findByUtilityType(String utilityType);
}
