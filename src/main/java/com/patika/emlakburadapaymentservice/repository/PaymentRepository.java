package com.patika.emlakburadapaymentservice.repository;

import com.patika.emlakburadapaymentservice.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findAllByUserId(Long userId);
}
