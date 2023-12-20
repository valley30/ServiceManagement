package com.repairs.service.repository;

import com.repairs.service.Entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Long> {
    // Tutaj możesz dodać niestandardowe metody zapytań, jeśli są potrzebne
}
