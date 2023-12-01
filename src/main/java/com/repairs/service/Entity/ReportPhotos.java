package com.repairs.service.Entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class ReportPhotos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long photoId;
    private String photoPath;
    private Date uploadDate;

    @ManyToOne
    @JoinColumn(name = "reportId")
    private Report report;

    public ReportPhotos() {
    }

    // Getters and Setters
    public Long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    // Dodatkowe metody, jeśli są potrzebne
}