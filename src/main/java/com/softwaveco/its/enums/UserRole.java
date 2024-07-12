package com.softwaveco.its.enums;

public enum UserRole {
    ROLE_USER,
    ROLE_MODERATOR,
    ROLE_ADMIN;

    public static UserRole fromString(String role) {
        for (UserRole userRole : UserRole.values()) {
            if (userRole.name().equalsIgnoreCase("ROLE_" + role)) {
                return userRole;
            }
        }
        return null;
    }
}
