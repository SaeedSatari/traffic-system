package com.softwaveco.its.service;

import com.softwaveco.its.data.entity.Supervisor;
import com.softwaveco.its.data.entity.User;
import com.softwaveco.its.data.repository.SupervisorRepository;
import com.softwaveco.its.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SupervisorService {
    private final SupervisorRepository supervisorRepository;
    private final UserRepository userRepository;

    public List<Supervisor> getAllSupervisors() {
        List<Supervisor> supervisors = supervisorRepository.findAll();
        if (supervisors.isEmpty()) {
            log.error("There is no supervisor");
            throw new EntityNotFoundException("There is no supervisor");
        }
        return supervisors;
    }

    public Supervisor getSupervisorById(String id) {
        Optional<Supervisor> optionalSupervisor = supervisorRepository.findById(id);
        if (optionalSupervisor.isPresent()) {
            return optionalSupervisor.get();
        } else {
            log.error("Supervisor {} not found", id);
            throw new EntityNotFoundException("Supervisor not found");
        }
    }

    @Transactional
    public void deleteSupervisor(String id) {
        Supervisor supervisor = getSupervisorById(id);
        supervisorRepository.delete(supervisor);
    }

    @Transactional
    public Supervisor registerSupervisor(String userId, String department, String designation) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            Supervisor supervisor = Supervisor.builder()
                    .user(optionalUser.get())
                    .designation(designation)
                    .department(department)
                    .build();
            return supervisorRepository.save(supervisor);
        } else {
            log.error("User {} not found", userId);
            throw new EntityNotFoundException("User Not Found");
        }
    }
}