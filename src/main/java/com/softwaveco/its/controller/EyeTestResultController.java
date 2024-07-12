package com.softwaveco.its.controller;

import com.softwaveco.its.data.entity.EyeTestResult;
import com.softwaveco.its.service.EyeTestResultService;
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
@RequestMapping(path = "/api/v1/eye-test-results")
@SecurityRequirement(name = "bearerAuth")
public class EyeTestResultController {
    private final EyeTestResultService eyeTestResultService;

    @GetMapping
    public List<EyeTestResult> getAllEyeTestResults() {
        return eyeTestResultService.getAllEyeTestResults();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EyeTestResult> getEyeTestResultById(@PathVariable String id) {
        return eyeTestResultService.getEyeTestResultById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public EyeTestResult createEyeTestResult(@RequestBody EyeTestResult eyeTestResult) {
        return eyeTestResultService.saveEyeTestResult(eyeTestResult);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEyeTestResult(@PathVariable String id) {
        eyeTestResultService.deleteEyeTestResult(id);
        return ResponseEntity.noContent().build();
    }
}
