package com.test.config;

public enum UserRole {

    ROLE_READER("INVESTOR"),
    ROLE_WRITER("USER"),
    ROLE_ADMIN("ADMIN");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
