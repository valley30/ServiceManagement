package com.repairs.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.repairs.service.Entity.Part;
import com.repairs.service.services.PartService;

import java.util.List;

@RestController
@RequestMapping("/api/parts")
public class PartController {

    @Autowired
    private PartService partService;

    @PostMapping("/add")
    public ResponseEntity<Part> addPart(@RequestBody Part part) {
        Part newPart = partService.addPart(part);
        return ResponseEntity.ok(newPart);
    }
    @GetMapping
    public List<Part> getAllParts() {
        return partService.findAllParts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Part> getPartById(@PathVariable Long id) {
        Part part = partService.findPartById(id)
                .orElseThrow(() -> new RuntimeException("Part not found"));
        return ResponseEntity.ok(part);
    }

    @PutMapping("/modify/{id}")
    public ResponseEntity<Part> updatePart(@PathVariable Long id, @RequestBody Part partDetails) {
        Part updatedPart = partService.updatePart(id, partDetails);
        return ResponseEntity.ok(updatedPart);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePart(@PathVariable Long id) {
        partService.deletePart(id);
        return ResponseEntity.ok("Part deleted successfully");
    }
}
    // Dodatkowe metody kontrolera, np. aktualizacja, usuwanie, wyszukiwanie

