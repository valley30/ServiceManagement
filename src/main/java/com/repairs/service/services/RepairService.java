package com.repairs.service.services;


import com.repairs.service.Entity.*;
import com.repairs.service.repository.*;
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
    private CustomerRepository customerRepository;

    @Autowired
    private AppUserRepository appUserRepository;
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
    public Repair updateRepair(Long id, Repair repairDetails) {
        Repair existingRepair = repairRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Repair not found"));

        // Map updated details onto the existing repair object
        existingRepair.setStatus(repairDetails.getStatus());
        existingRepair.setStartDate(repairDetails.getStartDate());
        existingRepair.setEndDate(repairDetails.getEndDate());
        existingRepair.setCustomerDescription(repairDetails.getCustomerDescription());
        existingRepair.setTechnicianDescription(repairDetails.getTechnicianDescription());
        existingRepair.setPrice(repairDetails.getPrice());
        // Map other fields as needed
        // ...

        return repairRepository.save(existingRepair);
    }
    public Repair getRepairById(Long id) {
        return repairRepository.findById(id).orElseThrow(() -> new RuntimeException("Repair not found"));
    }

    public Repair addRepair(Repair repair) {
        // Logika dodawania naprawy, w tym przypisanie customerId i inicjalizacja protokołu
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

    public String generatePdf(Repair repair) {
        String dest = "D:\\protocols\\protocol_" + repair.getRepairID() + ".pdf"; // Ścieżka i nazwa pliku

        try {
            String priceText;
            if (repair.getPrice() == null || repair.getPrice() == 0) {
                priceText = "Gwarancyjna naprawa";
            } else {
                priceText = "Kwota naprawy: " + repair.getPrice();
            }
            Customer customer = customerRepository.findById(repair.getCustomerId())
                    .orElseThrow(() -> new RuntimeException("Customer not found"));
            AppUser appUser = appUserRepository.findById(repair.getUserId())
                    .orElseThrow(() -> new RuntimeException("AppUser not found"));
            PdfWriter writer = new PdfWriter(dest);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph("Protokol Naprawy"));
            document.add(new Paragraph("ID Naprawy: " + repair.getRepairID()));
            // Dodaj inne informacje o naprawie
            document.add(new Paragraph("Opis Klienta: " + repair.getCustomerDescription()));
            document.add(new Paragraph("Opis Technika: " + repair.getTechnicianDescription()));

            document.add(new Paragraph("Imie i Nazwisko Klienta: " + customer.getFirstName() + " " + customer.getLastName()));

            document.add(new Paragraph("Technik: " + appUser.getEmail()));
            document.add(new Paragraph(priceText));
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
