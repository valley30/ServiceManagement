package com.repairs.service.services;

import com.repairs.service.Entity.Device;
import com.repairs.service.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    public Device addDevice(Device device) {
        return deviceRepository.save(device);
    }

    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    public void deleteDevice(Long id) {
        deviceRepository.deleteById(id);
    }
    public Device modifyDevice(Long id, Device deviceDetails) {
        Device device = deviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Device not found with id : " + id));


        device.setType(deviceDetails.getType());
        device.setManufacturer(deviceDetails.getManufacturer());
        device.setModel(deviceDetails.getModel());

        return deviceRepository.save(device);
    }


}
