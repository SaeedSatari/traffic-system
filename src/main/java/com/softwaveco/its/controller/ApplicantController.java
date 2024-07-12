package com.softwaveco.its.controller;


import com.softwaveco.its.controller.request.ApplicantRequest;
import com.softwaveco.its.controller.response.ApplicantResponse;
import com.softwaveco.its.controller.response.MessageResponse;
import com.softwaveco.its.data.entity.Applicant;
import com.softwaveco.its.service.ApplicantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@Slf4j
@RequestMapping(path = "/api/v1/applicants")
@SecurityRequirement(name = "bearerAuth")
public class ApplicantController {
    private final ApplicantService applicantService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "get list applicants")
    public List<ApplicantResponse> getAllApplicants() {
        log.info("getAllApplicants API...");
        List<Applicant> applicants = applicantService.getAllApplicants();
        return applicants.stream()
                .map(this::mapToApplicantResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "get a applicant")
    public ApplicantResponse getApplicantById(@PathVariable String id) {
        log.info("getApplicantById API...");
        Applicant applicant = applicantService.getApplicantById(id);
        return mapToApplicantResponse(applicant);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "create a new applicant")
    public ApplicantResponse createApplicant(@RequestBody ApplicantRequest request) {
        log.info("createApplicant API...");
        Applicant applicant = applicantService.registerApplicant(request.getUserId(), request.getDob(), request.getRemarks());
        return mapToApplicantResponse(applicant);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "delete a supervisor")
    public MessageResponse deleteApplicant(@PathVariable String id) {
        log.info("deleteApplicant API...");
        applicantService.deleteApplicant(id);
        return MessageResponse.builder()
                .message("Supervisor deleted")
                .httpStatus(HttpStatus.OK)
                .build();
    }

    private ApplicantResponse mapToApplicantResponse(Applicant applicant) {
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