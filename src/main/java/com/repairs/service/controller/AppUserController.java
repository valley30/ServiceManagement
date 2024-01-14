package com.repairs.service.controller;

import com.repairs.service.Entity.AppUser;
import com.repairs.service.Entity.Customer;
import com.repairs.service.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<AppUser>> getAllUsers() {
        List<AppUser> users = appUserService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    @GetMapping("/{id}")
    public ResponseEntity<AppUser> getAppUserById(@PathVariable Long id) {
        AppUser appUser = appUserService.findAppUserById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return ResponseEntity.ok(appUser);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        appUserService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/modify/{id}")
    public ResponseEntity<AppUser> modifyUser(@PathVariable Long id, @RequestBody AppUser userDetails) {
        AppUser updatedUser = appUserService.modifyUser(id, userDetails);
        return ResponseEntity.ok(updatedUser);
    }

}
