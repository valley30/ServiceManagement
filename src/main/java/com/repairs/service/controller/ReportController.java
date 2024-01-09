package com.repairs.service.controller;


import com.repairs.service.Entity.Report;
import com.repairs.service.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;


    @GetMapping
    public ResponseEntity<List<Report>> getAllReports() {
        List<Report> reports = reportService.getAllReports();
        return ResponseEntity.ok(reports);
    }

    @PostMapping("/add")
    public ResponseEntity<Report> addReport(@RequestBody Report reportRequest) {
        Report report = new Report();
        report.setCustomerId(reportRequest.getCustomerId());
        report.setUserId(reportRequest.getUserId());
        report.setClientDescription(reportRequest.getClientDescription());
        report.setStatus(reportRequest.getStatus());
        // inne ustawienia
        return ResponseEntity.ok(reportService.addReport(report));
    }

    // Pobierz szczegóły zgłoszenia
    @GetMapping("/{id}")
    public ResponseEntity<Report> getReport(@PathVariable Long id) {
        return ResponseEntity.ok(reportService.getReportById(id));
    }

    // Aktualizuj zgłoszenie
    @PutMapping("/modify/{id}")
    public ResponseEntity<Report> updateReport(@PathVariable Long id, @RequestBody Report report) {
        return ResponseEntity.ok(reportService.updateReport(id, report));
    }

    // Usuń zgłoszenie
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteReport(@PathVariable Long id) {
        reportService.deleteReport(id);
        return ResponseEntity.ok().build();
    }
}
