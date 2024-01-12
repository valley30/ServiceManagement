package com.repairs.service.model;

public class RepairPartRequest {
    private Long repairId;
    private Long partId;

    // Konstruktor, Gettery i Settery
    public RepairPartRequest() {
    }

    public Long getRepairId() {
        return repairId;
    }

    public void setRepairId(Long repairId) {
        this.repairId = repairId;
    }

    public Long getPartId() {
        return partId;
    }

    public void setPartId(Long partId) {
        this.partId = partId;
    }
}
