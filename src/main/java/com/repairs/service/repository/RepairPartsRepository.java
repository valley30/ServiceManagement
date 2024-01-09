package com.repairs.service.repository;

import com.repairs.service.Entity.Repair;
import com.repairs.service.Entity.RepairParts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepairPartsRepository extends JpaRepository<RepairParts, Long> {
    // Find all parts for a given repair
    List<RepairParts> findByRepair(Repair repair);




}