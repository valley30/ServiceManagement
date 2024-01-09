package com.repairs.service.services;


import com.repairs.service.Entity.Report;
import com.repairs.service.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;


    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }
    public Report addReport(Report report) {
        return reportRepository.save(report);
    }

    public Report getReportById(Long id) {
        return reportRepository.findById(id).orElseThrow(() -> new RuntimeException("Report not found"));
    }

    // W ReportService
    public Report updateReport(Long id, Report reportDetails) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Report not found"));

        report.setClientDescription(reportDetails.getClientDescription());
        report.setStatus(reportDetails.getStatus());
        report.setCustomerId(reportDetails.getCustomerId());
        report.setUserId(reportDetails.getUserId());
        // Ustawianie innych pól, jeśli jest taka potrzeba

        return reportRepository.save(report);
    }

    public void deleteReport(Long id) {
        reportRepository.deleteById(id);
    }

    // Inne metody, jeśli są potrzebne
}
