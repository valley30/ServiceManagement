package com.repairs.service.repository;

import com.repairs.service.Entity.Repair;
import com.repairs.service.Entity.RepairParts;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepairPartsRepository extends JpaRepository<RepairParts, Long> {

    List<RepairParts> findByRepair(Repair repair);




}