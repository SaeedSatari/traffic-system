package com.softwaveco.its.controller;

import com.softwaveco.its.controller.request.SupervisorRequest;
import com.softwaveco.its.controller.response.SupervisorResponse;
import com.softwaveco.its.data.entity.Supervisor;
import com.softwaveco.its.service.SupervisorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@Slf4j
@RequestMapping(path = "/api/v1/supervisors")
@SecurityRequirement(name = "bearerAuth")
public class SupervisorController {

    private final SupervisorService supervisorService;

    @GetMapping
    public List<Supervisor> getAllSupervisors() {
        return supervisorService.getAllSupervisors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Supervisor> getSupervisorById(@PathVariable String id) {
        return supervisorService.getSupervisorById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "create a new supervisor")
    public SupervisorResponse createSupervisor(@RequestBody SupervisorRequest request) {
        log.info("createSupervisor API...");
        Supervisor supervisor = supervisorService.registerSupervisor(request.getUserId(), request.getDepartment(),
                request.getDesignation());
        return SupervisorResponse.builder()
                .id(supervisor.getId())
                .username(supervisor.getUser().getUsername())
                .firstName(supervisor.getUser().getFirstName())
                .lastName(supervisor.getUser().getLastName())
                .department(supervisor.getDepartment())
                .designation(supervisor.getDesignation())
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Supervisor> updateSupervisor(@PathVariable String id, @RequestBody Supervisor supervisorDetails) {
        return supervisorService.getSupervisorById(id)
                .map(supervisor -> {
                    supervisor.setDepartment(supervisorDetails.getDepartment());
                    supervisor.setDesignation(supervisorDetails.getDesignation());
                    Supervisor updatedSupervisor = supervisorService.saveSupervisor(supervisor);
                    return ResponseEntity.ok(updatedSupervisor);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupervisor(@PathVariable String id) {
        supervisorService.deleteSupervisor(id);
        return ResponseEntity.noContent().build();
    }
}