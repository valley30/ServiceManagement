package com.repairs.service.services;


import com.repairs.service.Entity.Part;
import com.repairs.service.Entity.Repair;
import com.repairs.service.Entity.RepairParts;
import com.repairs.service.repository.PartRepository;
import com.repairs.service.repository.RepairPartsRepository;
import com.repairs.service.repository.RepairRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import java.util.List;
import java.util.Optional;

@Service
public class RepairService {

    @Autowired
    private RepairPartsRepository repairPartsRepository;


    @Autowired
    private RepairRepository repairRepository;

    @Autowired
    private PartRepository partRepository; // Inject the PartRepository
    public List<Repair> getAllRepairs() {
        return repairRepository.findAll();
    }
    public void addPartsToRepair(Long repairID, List<Long> partIDs) {
        Repair repair = getRepairById(repairID); // Ensure the repair exists

        for (Long partID : partIDs) {
            RepairParts repairPart = new RepairParts();
            Part part = partRepository.findById(partID).orElseThrow(() -> new RuntimeException("Part not found"));
            repairPart.setRepair(repair);
            repairPart.setPart(part);
            repairPartsRepository.save(repairPart);
        }
    }

    public void removePartFromRepair(Long repairPartID) {
        repairPartsRepository.deleteById(repairPartID);
    }


    @Transactional
    public Repair updateRepair(Long id, Repair repairDetails, List<Long> partIDs) {
        Repair repair = repairRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Repair not found"));

        // Map updated details onto the existing repair object
        repair.setStatus(repairDetails.getStatus());
        repair.setStartDate(repairDetails.getStartDate());
        repair.setEndDate(repairDetails.getEndDate());
        repair.setCustomerDescription(repairDetails.getCustomerDescription());
        repair.setTechnicianDescription(repairDetails.getTechnicianDescription());
        repair.setPrice(repairDetails.getPrice());
        // ... any other fields that need updating

        // First, remove existing parts associated with the repair
        List<RepairParts> existingParts = repairPartsRepository.findByRepair(repair);
        for (RepairParts rp : existingParts) {
            repairPartsRepository.delete(rp);
        }

        // Then, add new parts
        addPartsToRepair(id, partIDs);

        return repairRepository.save(repair);
    }
    public Repair getRepairById(Long id) {
        return repairRepository.findById(id).orElseThrow(() -> new RuntimeException("Repair not found"));
    }

    public Repair addRepair(Repair repair) {
        // Logika dodawania naprawy, w tym przypisanie customerId i inicjalizacja protokołu
        return repairRepository.save(repair);
    }

    public Repair updateRepair(Long id, Repair repairDetails) {
        Repair repair = repairRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Repair not found"));
        // Aktualizacja danych naprawy, w tym customerId i protokołu
        return repairRepository.save(repair);
    }
    public void generateRepairProtocol(Long repairId) {
        Repair repair = repairRepository.findById(repairId)
                .orElseThrow(() -> new RuntimeException("Repair not found"));

        // Logika generowania protokołu naprawy (PDF)
        // Możesz użyć biblioteki jak iText lub Apache PDFBox
        String protocolPath = generatePdf(repair);

        repair.setRepairProtocolPath(protocolPath);
        repairRepository.save(repair);
    }

    private String generatePdf(Repair repair) {
        String dest = "D:\\protocols\\protocol_" + repair.getRepairID() + ".pdf"; // Ścieżka i nazwa pliku

        try {
            PdfWriter writer = new PdfWriter(dest);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph("Protokół Naprawy"));
            document.add(new Paragraph("ID Naprawy: " + repair.getRepairID()));
            // Dodaj inne informacje o naprawie
            document.add(new Paragraph("Opis Klienta: " + repair.getCustomerDescription()));
            document.add(new Paragraph("Opis Technika: " + repair.getTechnicianDescription()));
            // Można dodać więcej szczegółów

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return dest; // Zwróć ścieżkę do wygenerowanego PDF
    }

    public void deleteRepair(Long id) {
        repairRepository.deleteById(id);
    }

    // Inne metody, jeśli są potrzebne
}
