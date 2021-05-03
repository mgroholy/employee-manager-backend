package com.codecool.employeemanager.model;

import org.springframework.security.core.GrantedAuthority;

public enum ClearanceLevel implements GrantedAuthority{
    ROLE_USER,
    ROLE_SUPERVISOR,
    ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }

}
