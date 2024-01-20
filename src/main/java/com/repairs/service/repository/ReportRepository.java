package com.repairs.service.repository;

import com.repairs.service.Entity.Repair;
import com.repairs.service.Entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    List<Report> findByUserId(Long userId);
}
