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

    @ManyToOne
    @JoinColumn(name = "UserID")
    private AppUser user;

    @ManyToOne
    @JoinColumn(name = "reportId")
    private Report report;

    @ManyToOne
    @JoinColumn(name = "DeviceID")
    private Device device;

    public Repair() {
    }

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

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }
}