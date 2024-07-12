package com.softwaveco.its.controller;

import com.softwaveco.its.controller.request.ApplicationRequest;
import com.softwaveco.its.controller.response.ApplicationResponse;
import com.softwaveco.its.data.entity.Application;
import com.softwaveco.its.mapper.ApplicantMapper;
import com.softwaveco.its.service.ApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@Slf4j
@RequestMapping(path = "/api/v1/applications")
@SecurityRequirement(name = "bearerAuth")
public class ApplicationController {
    private final ApplicationService applicationService;
    private final ApplicantMapper applicantMapper;

//    @GetMapping
//    public List<ApplicationDTO> getAllApplications() {
//        return applicationService.getAllApplications();
//    }
//
//    // Get application by ID
//    @GetMapping("/{id}")
//    public ResponseEntity<ApplicationDTO> getApplicationById(@PathVariable String id) {
//        return applicationService.getApplicationById(id)
//                .map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "submit a new application")
    public ApplicationResponse createApplication(@RequestBody ApplicationRequest request) {
        log.info("createApplication API...");
        Application application = applicationService.saveApplication(request.getApplicantId(), request.getLicenseType(),
                request.getRemarks(), request.getApplicationDate());
        return mapToApplicationResponse(application);
    }

//    // Update an existing application
//    @PutMapping("/{id}")
//    public ResponseEntity<ApplicationDTO> updateApplication(
//            @PathVariable String id,
//            @RequestBody ApplicationDTO applicationDTO) {
//        return applicationService.updateApplication(id, applicationDTO)
//                .map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    // Delete an application
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteApplication(@PathVariable String id) {
//        applicationService.deleteApplication(id);
//        return ResponseEntity.noContent().build();
//    }
//
//    // Change the status of an application
//    @PutMapping("/{id}/status")
//    public ResponseEntity<ApplicationDTO> updateApplicationStatus(
//            @PathVariable String id,
//            @RequestParam String status) {
//        return applicationService.updateApplicationStatus(id, status)
//                .map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }

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