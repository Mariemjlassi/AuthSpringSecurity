package com.projet.app.services;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.projet.app.model.UserEntity;

public class CustomUserDetails extends User {

    private Long id;

    public CustomUserDetails(UserEntity user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getEmail(), user.getPassword(), authorities);
        this.id = user.getId();
    }

    public Long getId() {
        return id;
    }
}
