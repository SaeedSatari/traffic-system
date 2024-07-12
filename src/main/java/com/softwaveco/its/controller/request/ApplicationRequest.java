package com.softwaveco.its.controller.request;

import com.softwaveco.its.enums.LicenceType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ApplicationRequest {
    private String applicantId;
    private LicenceType licenseType;
    private Date applicationDate;
    private String remarks;
}
