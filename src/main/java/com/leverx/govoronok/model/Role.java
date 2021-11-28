package com.leverx.govoronok.model;

public enum Role {
    ADMINISTRATOR("ADMINISTRATOR"),
    TRADER("TRADER"),
    USER("USER");

    private final String role;

    private Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

}
