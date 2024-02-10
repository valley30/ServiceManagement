package com.repairs.service.services;

import com.repairs.service.Entity.AppUser;
import com.repairs.service.Entity.Customer;
import com.repairs.service.Entity.Repair;
import com.repairs.service.Entity.Role;
import com.repairs.service.repository.AppUserRepository;
import com.repairs.service.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.repairs.service.repository.RepairRepository;
import java.util.List;
import java.util.Optional;

@Service
public class AppUserService {
    @Autowired
    private RepairRepository repairRepository;
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AppUser addUser(AppUser user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        if (user.getRoleName() != null && !user.getRoleName().isEmpty()) {
            Role role = roleRepository.findByName(user.getRoleName())
                    .orElseThrow(() -> new RuntimeException("Role not found with name: " + user.getRoleName()));
            user.setRole(role);
        }


        return appUserRepository.save(user);
    }
    public Optional<AppUser> findAppUserById(Long id) {
        return appUserRepository.findById(id);
    }

    public List<AppUser> getAllUsers() {
        return appUserRepository.findAll();
    }
    @Transactional
    public void deleteUser(Long id) {
        // Przypisanie napraw do innego użytkownika lub ustawienie userId jako null
        List<Repair> userRepairs = repairRepository.findByUserId(id);
        for (Repair repair : userRepairs) {
            repair.setUserId(null); // lub przypisz do innego użytkownika
            repairRepository.save(repair);
        }

        appUserRepository.deleteById(id);
    }
    public AppUser modifyUser(Long id, AppUser userDetails) {
        AppUser user = appUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id : " + id));


        if (userDetails.getUsername() != null && !userDetails.getUsername().isEmpty()) {
            user.setUsername(userDetails.getUsername());
        }


        if (userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()) {
            String encodedPassword = passwordEncoder.encode(userDetails.getPassword());
            user.setPassword(encodedPassword);
        }

        if (userDetails.getEmail() != null && !userDetails.getEmail().isEmpty()) {
            user.setEmail(userDetails.getEmail());
        }


        if (userDetails.getRole() != null) {
            user.setRole(userDetails.getRole());
        }

        return appUserRepository.save(user);
    }


}
