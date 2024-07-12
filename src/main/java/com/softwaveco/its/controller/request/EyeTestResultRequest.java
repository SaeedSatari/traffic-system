package com.softwaveco.its.controller.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EyeTestResultRequest {
    private String applicantId;
    private Date testDate;
    private String result;
    private String notes;
}
