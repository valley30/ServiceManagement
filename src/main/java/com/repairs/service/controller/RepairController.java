package com.repairs.service.controller;

import com.repairs.service.Entity.Repair;
import com.repairs.service.Entity.Report;
import com.repairs.service.repository.*;
import com.repairs.service.services.RepairService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

@RestController
@RequestMapping("/api/repairs")
public class RepairController {


    @Autowired
    private RepairRepository repairRepository;
    @Autowired
    private RepairService repairService;
    @Autowired
    private ReportRepository reportRepository;
    @GetMapping
    public ResponseEntity<List<Repair>> getAllRepairs() {
        List<Repair> repairs = repairService.getAllRepairs();
        return ResponseEntity.ok(repairs);
    }

    @PostMapping("/add")
    public ResponseEntity<Repair> addRepair(@RequestBody Repair repairRequest) {
        Repair repair = new Repair();
        // Ustawienie pól na podstawie danych z żądania
        repair.setCustomerId(repairRequest.getCustomerId());
        repair.setUserId(repairRequest.getUserId());
        repair.setDeviceId(repairRequest.getDeviceId());
        repair.setReportId(repairRequest.getReportId()); // Ustawienie reportId
        repair.setStatus(repairRequest.getStatus());
        repair.setStartDate(repairRequest.getStartDate());
        repair.setEndDate(repairRequest.getEndDate());
        repair.setCustomerDescription(repairRequest.getCustomerDescription());
        repair.setTechnicianDescription(repairRequest.getTechnicianDescription());
        repair.setPrice(repairRequest.getPrice());

        // Zapisanie naprawy do bazy danych
        Repair savedRepair = repairService.addRepair(repair);

        // Zwrócenie utworzonego obiektu w odpowiedzi
        return ResponseEntity.ok(savedRepair);
    }

    @GetMapping("/by-report/{reportId}")
    public ResponseEntity<Repair> getRepairByReportId(@PathVariable Long reportId) {
        Repair repair = repairService.findByReportId(reportId)
                .orElseThrow(() -> new RuntimeException("No repair found for report id: " + reportId));
        return ResponseEntity.ok(repair);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Repair> getRepair(@PathVariable Long id) {
        return ResponseEntity.ok(repairService.getRepairById(id));
    }

    @GetMapping("/generate-protocol/{id}")
    public void generateRepairProtocol(@PathVariable Long id, HttpServletResponse response) {
        Repair repair = repairRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Repair not found"));


        String protocolPath = repairService.generatePdf(repair);


        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=protocol_" + repair.getRepairID() + ".pdf");

        try (InputStream inputStream = new FileInputStream(protocolPath);
             OutputStream outputStream = response.getOutputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Repair>> getRepairsByUserId(@PathVariable Long userId) {
        List<Repair> userRepairs = repairRepository.findByUserId(userId);
        return ResponseEntity.ok(userRepairs);
    }

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



    @PutMapping("/modify/{id}")
    public ResponseEntity<Repair> updateRepair(@PathVariable Long id, @RequestBody Repair repairDetails) {
        return ResponseEntity.ok(repairService.updateRepair(id, repairDetails));
    }
}