package com.softwaveco.its.controller;

import com.softwaveco.its.controller.request.ApplicationRequest;
import com.softwaveco.its.controller.response.ApplicationResponse;
import com.softwaveco.its.data.entity.Application;
import com.softwaveco.its.enums.ApplicationStatus;
import com.softwaveco.its.mapper.ApplicantMapper;
import com.softwaveco.its.service.ApplicationService;
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
@RequestMapping(path = "/api/v1/applications")
@SecurityRequirement(name = "bearerAuth")
public class ApplicationController {
    private final ApplicationService applicationService;
    private final ApplicantMapper applicantMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "get list applications")
    public List<ApplicationResponse> getAllApplications() {
        log.info("getAllApplications API...");
        List<Application> applications = applicationService.getAllApplications();
        return applications.stream()
                .map(this::mapToApplicationResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "get an application")
    public ApplicationResponse getApplicationById(@PathVariable String id) {
        log.info("getApplicationById API...");
        Application application = applicationService.getApplicationById(id);
        return mapToApplicationResponse(application);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "submit a new application")
    public ApplicationResponse createApplication(@RequestBody ApplicationRequest request) {
        log.info("createApplication API...");
        Application application = applicationService.saveApplication(request.getApplicantId(), request.getLicenseType(),
                request.getRemarks(), request.getApplicationDate());
        return mapToApplicationResponse(application);
    }

    @PutMapping("/{id}/status")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "update the application status")
    public ApplicationResponse updateApplicationStatus(@PathVariable String id, @RequestParam ApplicationStatus status) {
        log.info("updateApplicationStatus API...");
        Application application = applicationService.updateApplicationStatus(id, status.name());
        return mapToApplicationResponse(application);
    }

    private ApplicationResponse mapToApplicationResponse(Application application) {
        return ApplicationResponse.builder()
                .id(application.getId())
                .applicationDate(application.getApplicationDate())
                .licenseType(application.getLicenseType())
                .remarks(application.getRemarks())
                .applicant(applicantMapper.mapToApplicantResponse(application.getApplicant()))
                .status(application.getStatus())
                .build();
    }
}