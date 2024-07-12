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
        return supervisorRepository.findAll();
    }

    public Optional<Supervisor> getSupervisorById(String id) {
        return supervisorRepository.findById(id);
    }

    public Supervisor saveSupervisor(Supervisor supervisor) {
        return supervisorRepository.save(supervisor);
    }

    public void deleteSupervisor(String id) {
        supervisorRepository.deleteById(id);
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