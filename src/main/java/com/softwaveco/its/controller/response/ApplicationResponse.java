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
public class ApplicationResponse {
    private String id;
    private ApplicantResponse applicant;
    private String licenseType;
    private String status;
    private Date applicationDate;
    private String remarks;
}
