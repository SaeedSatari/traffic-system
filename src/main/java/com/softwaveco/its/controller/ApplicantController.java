package com.softwaveco.its.controller;

import com.softwaveco.its.data.entity.Applicant;
import com.softwaveco.its.data.entity.User;
import com.softwaveco.its.service.ApplicantService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@Slf4j
@RequestMapping(path = "/api/v1/applicants")
@SecurityRequirement(name = "bearerAuth")
public class ApplicantController {
    private final ApplicantService applicantService;

    @GetMapping
    public List<Applicant> getAllApplicants() {
        return applicantService.getAllApplicants();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Applicant> getApplicantById(@PathVariable String id) {
        return applicantService.getApplicantById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Applicant createApplicant(@RequestBody Applicant applicant, @RequestBody User user) {
        return applicantService.registerApplicant(applicant, user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Applicant> updateApplicant(@PathVariable String id, @RequestBody Applicant applicantDetails) {
        return applicantService.getApplicantById(id)
                .map(applicant -> {
                    applicant.setDob(applicantDetails.getDob());
                    applicant.setRemarks(applicantDetails.getRemarks());
                    Applicant updatedApplicant = applicantService.saveApplicant(applicant);
                    return ResponseEntity.ok(updatedApplicant);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplicant(@PathVariable String id) {
        applicantService.deleteApplicant(id);
        return ResponseEntity.noContent().build();
    }
}