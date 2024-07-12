package com.softwaveco.its.controller;

import com.softwaveco.its.controller.request.EyeTestResultRequest;
import com.softwaveco.its.controller.response.EyeTestResultResponse;
import com.softwaveco.its.data.entity.EyeTestResult;
import com.softwaveco.its.mapper.ApplicantMapper;
import com.softwaveco.its.service.EyeTestResultService;
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
@RequestMapping(path = "/api/v1/eye-test-results")
@SecurityRequirement(name = "bearerAuth")
public class EyeTestResultController {
    private final EyeTestResultService eyeTestResultService;
    private final ApplicantMapper applicantMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "get list eye tests")
    public List<EyeTestResultResponse> getAllEyeTestResults() {
        log.info("getAllEyeTestResults API...");
        List<EyeTestResult> testResults = eyeTestResultService.getAllEyeTestResults();
        return testResults.stream()
                .map(this::mapToEyeTestResultResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "get an eye test result")
    public EyeTestResultResponse getEyeTestResultById(@PathVariable String id) {
        log.info("getEyeTestResultById API...");
        EyeTestResult eyeTestResult = eyeTestResultService.getEyeTestResultById(id);
        return mapToEyeTestResultResponse(eyeTestResult);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "assign eye test to applicant")
    public EyeTestResultResponse createEyeTestResult(@RequestBody EyeTestResultRequest request) {
        log.info("createEyeTestResult API...");
        EyeTestResult eyeTestResult = eyeTestResultService.saveEyeTestResult(request.getApplicantId(), request.getTestDate(),
                request.getResult(), request.getNotes());
        return mapToEyeTestResultResponse(eyeTestResult);
    }

    private EyeTestResultResponse mapToEyeTestResultResponse(EyeTestResult eyeTestResult) {
        return EyeTestResultResponse.builder()
                .id(eyeTestResult.getId())
                .notes(eyeTestResult.getNotes())
                .testDate(eyeTestResult.getTestDate())
                .result(eyeTestResult.getResult())
                .applicant(applicantMapper.mapToApplicantResponse(eyeTestResult.getApplicant()))
                .build();
    }
}
