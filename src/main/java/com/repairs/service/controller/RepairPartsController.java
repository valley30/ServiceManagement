package com.repairs.service.controller;

// ...[importy]

import com.repairs.service.Entity.RepairParts;
import com.repairs.service.Entity.Repair;
import com.repairs.service.Entity.Part;
import com.repairs.service.model.RepairPartRequest;
import com.repairs.service.repository.RepairPartsRepository;
import com.repairs.service.repository.RepairRepository;
import com.repairs.service.repository.PartRepository;
import com.repairs.service.services.RepairPartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/repair-parts")
public class RepairPartsController {

    @Autowired
    private RepairPartsRepository repairPartsRepository;

    @Autowired
    private RepairRepository repairRepository;

    @Autowired
    private RepairPartsService repairPartsService;
    @Autowired
    private PartRepository partRepository;


    @PostMapping("/add")
    public ResponseEntity<?> addPartToRepair(@RequestBody RepairPartRequest request) {
        Repair repair = repairRepository.findById(request.getRepairId())
                .orElseThrow(() -> new RuntimeException("Repair not found"));
        Part part = partRepository.findById(request.getPartId())
                .orElseThrow(() -> new RuntimeException("Part not found"));

        RepairParts repairPart = new RepairParts();
        repairPart.setRepair(repair);
        repairPart.setPart(part);
        repairPartsRepository.save(repairPart);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/{repairId}")
    public ResponseEntity<List<RepairParts>> getPartsForRepair(@PathVariable Long repairId) {
        Repair repair = repairRepository.findById(repairId)
                .orElseThrow(() -> new RuntimeException("Repair not found"));
        List<RepairParts> parts = repairPartsRepository.findByRepair(repair);
        return ResponseEntity.ok(parts);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRepairParts(@PathVariable Long id) {
        repairPartsService.deleteRepairPart(id);
        return ResponseEntity.ok("RepairParts has been deleted");
    }

}
