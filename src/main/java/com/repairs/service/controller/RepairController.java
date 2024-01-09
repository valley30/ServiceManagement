package com.repairs.service.controller;

import com.repairs.service.Entity.Repair;
import com.repairs.service.repository.CustomerRepository;
import com.repairs.service.repository.DeviceRepository;
import com.repairs.service.repository.RepairRepository;
import com.repairs.service.repository.UserRepository;
import com.repairs.service.services.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/repairs")
public class RepairController {


    @Autowired
    private RepairRepository repairRepository;
    @Autowired
    private RepairService repairService;

    @GetMapping
    public ResponseEntity<List<Repair>> getAllRepairs() {
        List<Repair> repairs = repairService.getAllRepairs();
        return ResponseEntity.ok(repairs);
    }

    @PostMapping("/add")
    public ResponseEntity<Repair> addRepair(@RequestBody Repair repairRequest) {
        Repair repair = new Repair();
        repair.setCustomerId(repairRequest.getCustomerId());
        repair.setUserId(repairRequest.getUserId());
        repair.setDeviceId(repairRequest.getDeviceId());

        // Przypisanie pozostałych pól z repairRequest do nowego obiektu repair
        repair.setStatus(repairRequest.getStatus());
        repair.setStartDate(repairRequest.getStartDate());
        repair.setEndDate(repairRequest.getEndDate());
        repair.setCustomerDescription(repairRequest.getCustomerDescription());
        repair.setTechnicianDescription(repairRequest.getTechnicianDescription());
        repair.setPrice(repairRequest.getPrice());
        // Dodaj inne pola, które mogą być potrzebne

        return ResponseEntity.ok(repairService.addRepair(repair));
    }




    // Pobierz szczegóły naprawy
    @GetMapping("/{id}")
    public ResponseEntity<Repair> getRepair(@PathVariable Long id) {
        return ResponseEntity.ok(repairService.getRepairById(id));
    }

    @PostMapping("/generate-protocol/{id}")
    public ResponseEntity<?> generateRepairProtocol(@PathVariable Long id) {
        repairService.generateRepairProtocol(id);
        return ResponseEntity.ok().build(); // Można zwrócić też informację o sukcesie
    }



    // Usuń naprawę
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRepair(@PathVariable Long id) {
        repairService.deleteRepair(id);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/{id}/add-parts")
    public ResponseEntity<?> addPartsToRepair(@PathVariable Long id, @RequestBody List<Long> partIDs) {
        repairService.addPartsToRepair(id, partIDs);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/remove-part/{repairPartID}")
    public ResponseEntity<?> removePartFromRepair(@PathVariable Long repairPartID) {
        repairService.removePartFromRepair(repairPartID);
        return ResponseEntity.ok().build();
    }

    // Update the updateRepair method to accept a list of part IDs
    @PutMapping("/modify/{id}")
    public ResponseEntity<Repair> updateRepair(@PathVariable Long id, @RequestBody Repair repair, @RequestBody List<Long> partIDs) {
        return ResponseEntity.ok(repairService.updateRepair(id, repair, partIDs));
    }
}
