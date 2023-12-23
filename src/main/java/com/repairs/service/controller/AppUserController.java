package com.repairs.service.controller;

import com.repairs.service.Entity.AppUser;
import com.repairs.service.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @PostMapping("/add")
    public ResponseEntity<AppUser> addUser(@RequestBody AppUser user) {
        AppUser newUser = appUserService.addUser(user);
        return ResponseEntity.ok(newUser);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        appUserService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    // Inne endpointy...
}
