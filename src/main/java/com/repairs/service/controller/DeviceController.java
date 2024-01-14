package com.repairs.service.controller;

import com.repairs.service.Entity.Device;
import com.repairs.service.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devices")

public class DeviceController {


    @Autowired
    private DeviceService deviceService;

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<Device> addDevice(@RequestBody Device device) {
        Device newDevice = deviceService.addDevice(device);
        return ResponseEntity.ok(newDevice);
    }

    @GetMapping
    public ResponseEntity<List<Device>> getAllDevices() {
        List<Device> devices = deviceService.getAllDevices();
        return ResponseEntity.ok(devices);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDevice(@PathVariable Long id) {
        deviceService.deleteDevice(id);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/modify/{id}")
    public ResponseEntity<Device> modifyDevice(@PathVariable Long id, @RequestBody Device deviceDetails) {
        Device updatedDevice = deviceService.modifyDevice(id, deviceDetails);
        return ResponseEntity.ok(updatedDevice);
    }

}
