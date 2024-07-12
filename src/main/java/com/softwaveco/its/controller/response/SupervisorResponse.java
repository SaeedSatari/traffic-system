package com.softwaveco.its.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SupervisorResponse {
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String department;
    private String designation;
}
