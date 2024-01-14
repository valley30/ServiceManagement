package com.repairs.service.repository;

import com.repairs.service.Entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartRepository extends JpaRepository<Part, Long> {

    Part findByName(String name);
}