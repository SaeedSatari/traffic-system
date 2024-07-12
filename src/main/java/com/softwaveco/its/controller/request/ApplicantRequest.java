package com.softwaveco.its.controller.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ApplicantRequest {
    private String userId;
    private Date dob;
    private String remarks;
}
