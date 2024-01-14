package com.repairs.service.services;

import com.repairs.service.Entity.AppUser;
import com.repairs.service.Entity.Customer;
import com.repairs.service.Entity.Role;
import com.repairs.service.repository.AppUserRepository;
import com.repairs.service.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserService {
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
    public void deleteUser(Long id) {
        appUserRepository.deleteById(id);
    }
    public AppUser modifyUser(Long id, AppUser userDetails) {
        AppUser user = appUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id : " + id));

        // Aktualizacja danych użytkownika tylko jeśli zostały dostarczone
        if (userDetails.getUsername() != null && !userDetails.getUsername().isEmpty()) {
            user.setUsername(userDetails.getUsername());
        }

        // Aktualizacja hasła tylko jeśli zostało dostarczone
        if (userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()) {
            String encodedPassword = passwordEncoder.encode(userDetails.getPassword());
            user.setPassword(encodedPassword);
        }

        // Aktualizacja emaila tylko jeśli został dostarczony
        if (userDetails.getEmail() != null && !userDetails.getEmail().isEmpty()) {
            user.setEmail(userDetails.getEmail());
        }

        // Aktualizacja roli tylko jeśli została dostarczona
        if (userDetails.getRole() != null) {
            user.setRole(userDetails.getRole());
        }

        return appUserRepository.save(user);
    }

    // Inne metody...
}
