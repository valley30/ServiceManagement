package com.repairs.service.services;

import com.repairs.service.Entity.Device;
import com.repairs.service.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    public Device addDevice(Device device) {
        return deviceRepository.save(device);
    }

    // Tutaj można dodać więcej metod, np. do wyszukiwania, aktualizacji, usuwania urządzeń
}
