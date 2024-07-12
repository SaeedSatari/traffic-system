package com.softwaveco.its.service;

import com.softwaveco.its.data.entity.EyeTestResult;
import com.softwaveco.its.data.repository.EyeTestResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EyeTestResultService {
    @Autowired
    private EyeTestResultRepository eyeTestResultRepository;

    public List<EyeTestResult> getAllEyeTestResults() {
        return eyeTestResultRepository.findAll();
    }

    public Optional<EyeTestResult> getEyeTestResultById(String id) {
        return eyeTestResultRepository.findById(id);
    }

    public EyeTestResult saveEyeTestResult(EyeTestResult eyeTestResult) {
        return eyeTestResultRepository.save(eyeTestResult);
    }

    public void deleteEyeTestResult(String id) {
        eyeTestResultRepository.deleteById(id);
    }
}