package com.softwaveco.its.mapper;

import com.softwaveco.its.controller.response.ApplicantResponse;
import com.softwaveco.its.data.entity.Applicant;
import org.springframework.stereotype.Component;

@Component
public class ApplicantMapper {
    public ApplicantResponse mapToApplicantResponse(Applicant applicant) {
        return ApplicantResponse.builder()
                .id(applicant.getId())
                .username(applicant.getUser().getUsername())
                .firstName(applicant.getUser().getFirstName())
                .lastName(applicant.getUser().getLastName())
                .dob(applicant.getDob())
                .remarks(applicant.getRemarks())
                .build();
    }
}
