package com.repairs.service.services;


import com.repairs.service.repository.RepairPartsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepairPartsService {
    @Autowired
    RepairPartsRepository repairPartsRepository;

    public void  deleteRepairPart(Long id) {
        repairPartsRepository.deleteById(id);
    }
    // Dodatkowe metody serwisu, np. aktualizacja, usuwanie, wyszukiwanie
}
