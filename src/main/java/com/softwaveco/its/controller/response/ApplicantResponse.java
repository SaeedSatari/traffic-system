package com.softwaveco.its.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicantResponse {
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private Date dob;
    private String remarks;
}
