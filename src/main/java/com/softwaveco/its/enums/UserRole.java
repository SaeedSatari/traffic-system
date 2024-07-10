package com.softwaveco.its.enums;

public enum UserRole {
    ROLE_SUPER_ADMIN,
    ROLE_OWNER,
    ROLE_ADMIN,
    ROLE_CONTRIBUTOR;

    public static UserRole fromString(String role) {
        for (UserRole userRole : UserRole.values()) {
            if (userRole.name().equalsIgnoreCase("ROLE_" + role)) {
                return userRole;
            }
        }
        return null;
    }
}
