package com.softwaveco.its.service;

import com.softwaveco.its.data.entity.Applicant;
import com.softwaveco.its.data.entity.Application;
import com.softwaveco.its.data.repository.ApplicationRepository;
import com.softwaveco.its.enums.ApplicationStatus;
import com.softwaveco.its.enums.LicenceType;
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
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final ApplicantService applicantService;

    public List<Application> getAllApplications() {
        List<Application> applications = applicationRepository.findAll();
        if (applications.isEmpty()) {
            log.error("There is no applications");
            throw new EntityNotFoundException("There is no applications");
        }
        return applications;
    }

    public Application getApplicationById(String id) {
        Optional<Application> optionalTest = applicationRepository.findById(id);
        if (optionalTest.isPresent()) {
            return optionalTest.get();
        } else {
            log.error("Application {} not found", id);
            throw new EntityNotFoundException("Application not found");
        }
    }

    @Transactional
    public Application saveApplication(String applicantId, LicenceType licenseType,
                                       String remarks, Date applicationDate) {
        Applicant applicant = applicantService.getApplicantById(applicantId);
        Application application = Application.builder()
                .applicant(applicant)
                .status(ApplicationStatus.PENDING.name())
                .remarks(remarks)
                .licenseType(licenseType.name())
                .applicationDate(applicationDate)
                .build();
        return applicationRepository.save(application);
    }

    @Transactional
    public Application updateApplicationStatus(String id, String status) {
        Application application = getApplicationById(id);
        application.setStatus(status);
        return applicationRepository.save(application);
    }
}