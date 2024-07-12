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
public class EyeTestResultResponse {
    private String id;
    private Date testDate;
    private String result;
    private String notes;
    private ApplicantResponse applicant;
}
