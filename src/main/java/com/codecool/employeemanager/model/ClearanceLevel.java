package com.codecool.employeemanager.model;

import org.springframework.security.core.GrantedAuthority;

public enum ClearanceLevel implements GrantedAuthority{
    USER,
    SUPERVISOR,
    ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }

}
