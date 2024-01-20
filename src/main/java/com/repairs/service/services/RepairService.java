package com.repairs.service.services;


import com.itextpdf.layout.property.TextAlignment;
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
    private PartRepository partRepository;
    public List<Repair> getAllRepairs() {
        return repairRepository.findAll();
    }
    public void addPartsToRepair(Long repairID, List<Long> partIDs) {
        Repair repair = getRepairById(repairID);

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


        existingRepair.setStatus(repairDetails.getStatus());
        existingRepair.setStartDate(repairDetails.getStartDate());
        if (repairDetails.getEndDate() != null) {
            existingRepair.setEndDate(repairDetails.getEndDate());
        }
        existingRepair.setCustomerDescription(repairDetails.getCustomerDescription());
        existingRepair.setTechnicianDescription(repairDetails.getTechnicianDescription());
        existingRepair.setPrice(repairDetails.getPrice());
        existingRepair.setDeviceId(repairDetails.getDeviceId());


        return repairRepository.save(existingRepair);
    }
    public Repair getRepairById(Long id) {
        return repairRepository.findById(id).orElseThrow(() -> new RuntimeException("Repair not found"));
    }

    public Repair addRepair(Repair repair) {

        return repairRepository.save(repair);
    }


    public void generateRepairProtocol(Long repairId) {
        Repair repair = repairRepository.findById(repairId)
                .orElseThrow(() -> new RuntimeException("Repair not found"));


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


            Paragraph titleParagraph = new Paragraph("Protokol Naprawy")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFontSize(20);
            document.add(titleParagraph);

            document.add(new Paragraph("PODPIS KLIENTA\n\n\n\n................")
                    .setFixedPosition(350, 50, 200)
                    .setTextAlignment(TextAlignment.RIGHT));

            document.add(new Paragraph("ID Naprawy: " + repair.getRepairID()).setMarginLeft(15));
            document.add(new Paragraph("Opis Klienta: " + repair.getCustomerDescription()).setMarginLeft(15));
            document.add(new Paragraph("Opis Technika: " + repair.getTechnicianDescription()).setMarginLeft(15));
            document.add(new Paragraph("Imie i Nazwisko Klienta: " + customer.getFirstName() + " " + customer.getLastName()).setMarginLeft(15));


            Paragraph technicianParagraph = new Paragraph("Naprawe wykonal:\nTechnik: " + appUser.getEmail() + "\n" + priceText)
                    .setFixedPosition(350, 400
                            , 200)
                    .setTextAlignment(TextAlignment.RIGHT);

            document.add(technicianParagraph);


            document.add(new Paragraph("PODPIS KLIENTA\n\n\n\n................")
                    .setFixedPosition(350, 50, 200)
                    .setTextAlignment(TextAlignment.RIGHT));

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return dest;
    }


    public void deleteRepair(Long id) {
        repairRepository.deleteById(id);
    }


}
