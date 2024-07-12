package com.softwaveco.its.service;

import com.softwaveco.its.data.entity.Applicant;
import com.softwaveco.its.data.entity.EyeTestResult;
import com.softwaveco.its.data.repository.EyeTestResultRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EyeTestResultService {
    private final EyeTestResultRepository eyeTestResultRepository;
    private final ApplicantService applicantService;

    public List<EyeTestResult> getAllEyeTestResults() {
        List<EyeTestResult> testResults = eyeTestResultRepository.findAll();
        if (testResults.isEmpty()) {
            log.error("There is no eye tests");
            throw new EntityNotFoundException("There is no eye tests");
        }
        return testResults;
    }

    public EyeTestResult getEyeTestResultById(String id) {
        Optional<EyeTestResult> optionalTest = eyeTestResultRepository.findById(id);
        if (optionalTest.isPresent()) {
            return optionalTest.get();
        } else {
            log.error("Eye test {} not found", id);
            throw new EntityNotFoundException("Eye test not found");
        }
    }

    public EyeTestResult saveEyeTestResult(String applicantId, Date testDate, String result, String notes) {
        Applicant applicant = applicantService.getApplicantById(applicantId);
        return EyeTestResult.builder()
                .applicant(applicant)
                .result(result)
                .testDate(testDate)
                .notes(notes)
                .build();
    }
}