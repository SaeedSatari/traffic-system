package com.softwaveco.its.service;

import com.softwaveco.its.data.entity.Applicant;
import com.softwaveco.its.data.entity.User;
import com.softwaveco.its.data.repository.ApplicantRepository;
import com.softwaveco.its.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApplicantService {
    private final ApplicantRepository applicantRepository;
    private final UserRepository userRepository;

    public List<Applicant> getAllApplicants() {
        List<Applicant> applicants = applicantRepository.findAll();
        if (applicants.isEmpty()) {
            log.error("There is no applicants");
            throw new EntityNotFoundException("There is no applicants");
        }
        return applicants;
    }

    public Applicant getApplicantById(String id) {
        Optional<Applicant> optionalApplicant = applicantRepository.findById(id);
        if (optionalApplicant.isPresent()) {
            return optionalApplicant.get();
        } else {
            log.error("Applicant {} not found", id);
            throw new EntityNotFoundException("Applicant not found");
        }
    }

    @Transactional
    public void deleteApplicant(String id) {
        Applicant applicant = getApplicantById(id);
        applicantRepository.delete(applicant);
    }

    @Transactional
    public Applicant registerApplicant(String userId, Date dob, String remarks) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            Applicant applicant = Applicant.builder()
                    .user(optionalUser.get())
                    .dob(dob)
                    .remarks(remarks)
                    .build();
            return applicantRepository.save(applicant);
        } else {
            log.error("User {} not found", userId);
            throw new EntityNotFoundException("User Not Found");
        }
    }
}