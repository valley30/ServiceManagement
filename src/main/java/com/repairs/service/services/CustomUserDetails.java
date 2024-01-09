package com.repairs.service.services;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUserDetails extends User {
    private Long userID;

    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, Long userID) {
        super(username, password, authorities);
        this.userID = userID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }
}
