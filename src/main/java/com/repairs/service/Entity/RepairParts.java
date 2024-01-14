package com.repairs.service.Entity;


import jakarta.persistence.*;

@Entity
public class RepairParts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long repairPartID;

    @ManyToOne
    @JoinColumn(name = "RepairID")
    private Repair repair;

    @ManyToOne
    @JoinColumn(name = "PartID")
    private Part part;

    public RepairParts() {
    }


    public Long getRepairPartID() {
        return repairPartID;
    }

    public void setRepairPartID(Long repairPartID) {
        this.repairPartID = repairPartID;
    }

    public Repair getRepair() {
        return repair;
    }

    public void setRepair(Repair repair) {
        this.repair = repair;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }


}