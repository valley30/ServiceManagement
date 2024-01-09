package com.repairs.service.repository;

import com.repairs.service.Entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    // Możesz dodać niestandardowe metody wyszukiwania, jeśli są potrzebne

}
