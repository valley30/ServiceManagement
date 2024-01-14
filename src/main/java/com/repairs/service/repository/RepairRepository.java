package com.repairs.service.repository;

import com.repairs.service.Entity.Repair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepairRepository extends JpaRepository<Repair, Long> {
    // Możesz dodać niestandardowe metody wyszukiwania, jeśli są potrzebne
    List<Repair> findByUserId(Long userId);
}
