package com.jeiup.fitnessap.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Custom implementation of Spring Security's UserDetails.
 * This class represents the security details of an application user.
 */
public final class MyUserDetails implements UserDetails {

    private static final long serialVersionUID = 1L;

    private final String userName;
    private final String password;
    private final boolean active;
    private final Set<GrantedAuthority> authorities;

    /**
     * Constructs a MyUserDetails instance from a User entity.
     *
     * @param user the user entity (must not be null)
     * @throws IllegalArgumentException if user is null or if required fields are invalid
     */
    public MyUserDetails(User user) {
        Objects.requireNonNull(user, "User cannot be null");
        
        this.userName = validateUsername(user.getUserName());
        this.password = validatePassword(user.getPassword());
        this.active = user.isActive();
        this.authorities = Collections.unmodifiableSet(
            Arrays.stream(user.getRoles().split(","))
                .map(String::trim)
                .filter(role -> !role.isEmpty())
                .map(role -> role.startsWith("ROLE_") ? role : "ROLE_" + role)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet())
        );
    }

    private String validateUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        return username;
    }

    private String validatePassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
