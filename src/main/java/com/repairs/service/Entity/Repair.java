package com.repairs.service.Entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Repair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long repairID;
    private String status;
    private Date startDate;
    private Date endDate;
    private String customerDescription;
    private String technicianDescription;
    private Double price;

    @Column(name = "UserID")
    private Long userId; // Zmieniono z AppUser na Long

    @Column(name = "DeviceID")
    private Long deviceId; // Zmieniono z Device na Long

    @Column(name = "CustomerID")
    private Long customerId; // Zmieniono z Customer na Long

    @Column(name = "ReportID")
    private Long reportId; // Dodano kolumnę dla ID zgłoszenia

    private String repairProtocolPath; // Ścieżka do protokołu naprawy


    public Long getRepairID() {
        return repairID;
    }

    public void setRepairID(Long repairID) {
        this.repairID = repairID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getCustomerDescription() {
        return customerDescription;
    }

    public void setCustomerDescription(String customerDescription) {
        this.customerDescription = customerDescription;
    }

    public String getTechnicianDescription() {
        return technicianDescription;
    }

    public void setTechnicianDescription(String technicianDescription) {
        this.technicianDescription = technicianDescription;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }
    public String getRepairProtocolPath() {
        return repairProtocolPath;
    }

    public void setRepairProtocolPath(String repairProtocolPath) {
        this.repairProtocolPath = repairProtocolPath;
    }
}