package com.repairs.service.services;

import com.repairs.service.Entity.AppUser;
import com.repairs.service.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {
    @Autowired
    private AppUserRepository appUserRepository;

    public AppUser addUser(AppUser user) {
        return appUserRepository.save(user);
    }

    public void deleteUser(Long id) {
        appUserRepository.deleteById(id);
    }

    // Inne metody...
}
