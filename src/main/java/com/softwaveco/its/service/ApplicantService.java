package com.softwaveco.its.service;

import com.softwaveco.its.data.entity.Applicant;
import com.softwaveco.its.data.entity.User;
import com.softwaveco.its.data.repository.ApplicantRepository;
import com.softwaveco.its.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicantService {
    @Autowired
    private ApplicantRepository applicantRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Applicant> getAllApplicants() {
        return applicantRepository.findAll();
    }

    public Optional<Applicant> getApplicantById(String id) {
        return applicantRepository.findById(id);
    }

    public Applicant saveApplicant(Applicant applicant) {
        return applicantRepository.save(applicant);
    }

    public void deleteApplicant(String id) {
        applicantRepository.deleteById(id);
    }

    public Applicant registerApplicant(Applicant applicant, User user) {
        userRepository.save(user);
        applicant.setUser(user);
        return applicantRepository.save(applicant);
    }
}