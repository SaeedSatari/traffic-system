package com.softwaveco.its.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDetailsResponse {
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private boolean isVerified;
    private List<String> roles;
}
