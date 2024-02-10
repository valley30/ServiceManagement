package com.repairs.service.repository;

import com.repairs.service.Entity.Repair;
import com.repairs.service.Entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepairRepository extends JpaRepository<Repair, Long> {

    List<Repair> findByUserId(Long userId);


    Optional<Repair> findByReportId(Long reportId);
}
