package com.repairs.service.controller;


import com.repairs.service.Entity.Repair;
import com.repairs.service.Entity.Report;
import com.repairs.service.repository.ReportRepository;
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

    @Autowired
    private ReportRepository reportRepository;

    @GetMapping
    public ResponseEntity<List<Report>> getAllReports() {
        List<Report> reports = reportService.getAllReports();
        return ResponseEntity.ok(reports);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Report>> getReportsByUserId(@PathVariable Long userId) {
        List<Report> userReports = reportRepository.findByUserId(userId);
        return ResponseEntity.ok(userReports);
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


    @GetMapping("/{id}")
    public ResponseEntity<Report> getReport(@PathVariable Long id) {
        return ResponseEntity.ok(reportService.getReportById(id));
    }


    @PutMapping("/modify/{id}")
    public ResponseEntity<Report> updateReport(@PathVariable Long id, @RequestBody Report report) {
        return ResponseEntity.ok(reportService.updateReport(id, report));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteReport(@PathVariable Long id) {
        reportService.deleteReport(id);
        return ResponseEntity.ok().build();
    }
}
