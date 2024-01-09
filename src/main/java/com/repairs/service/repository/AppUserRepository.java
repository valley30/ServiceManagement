package com.repairs.service.repository;
import com.repairs.service.Entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    // Metody specyficzne dla AppUser

}