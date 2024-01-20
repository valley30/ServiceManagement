package com.repairs.service.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.repairs.service.Entity.Part;
import com.repairs.service.repository.PartRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PartService {

    @Autowired
    private PartRepository partRepository;

    public Part addPart(Part part) {
        return partRepository.save(part);
    }

    public List<Part> findAllParts() {
        return partRepository.findAll();
    }

    public Optional<Part> findPartById(Long id) {
        return partRepository.findById(id);
    }

    public Part updatePart(Long id, Part partDetails) {
        Part part = partRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Part not found"));
        part.setName(partDetails.getName());
        part.setPrice(partDetails.getPrice());
        return partRepository.save(part);
    }

    public void deletePart(Long id) {
        partRepository.deleteById(id);
    }
}


