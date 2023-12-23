package com.repairs.service.repository;

import com.repairs.service.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Metody specyficzne dla Customer
    Customer findByEmail(String email);
}