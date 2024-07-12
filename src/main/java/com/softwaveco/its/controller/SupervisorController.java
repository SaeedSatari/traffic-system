package com.softwaveco.its.controller;

import com.softwaveco.its.controller.request.SupervisorRequest;
import com.softwaveco.its.controller.response.MessageResponse;
import com.softwaveco.its.controller.response.SupervisorResponse;
import com.softwaveco.its.data.entity.Supervisor;
import com.softwaveco.its.service.SupervisorService;
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
@RequestMapping(path = "/api/v1/supervisors")
@SecurityRequirement(name = "bearerAuth")
public class SupervisorController {

    private final SupervisorService supervisorService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "get list supervisors")
    public List<SupervisorResponse> getAllSupervisors() {
        log.info("getAllSupervisors API...");
        List<Supervisor> supervisors = supervisorService.getAllSupervisors();
        return supervisors.stream()
                .map(this::mapToSupervisorResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "get a supervisor")
    public SupervisorResponse getSupervisorById(@PathVariable String id) {
        log.info("getSupervisorById API...");
        Supervisor supervisor = supervisorService.getSupervisorById(id);
        return mapToSupervisorResponse(supervisor);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "create a new supervisor")
    public SupervisorResponse createSupervisor(@RequestBody SupervisorRequest request) {
        log.info("createSupervisor API...");
        Supervisor supervisor = supervisorService.registerSupervisor(request.getUserId(), request.getDepartment(),
                request.getDesignation());
        return mapToSupervisorResponse(supervisor);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "delete a supervisor")
    public MessageResponse deleteSupervisor(@PathVariable String id) {
        log.info("deleteSupervisor API...");
        supervisorService.deleteSupervisor(id);
        return MessageResponse.builder()
                .message("Supervisor deleted")
                .httpStatus(HttpStatus.OK)
                .build();
    }

    private SupervisorResponse mapToSupervisorResponse(Supervisor supervisor) {
        return SupervisorResponse.builder()
                .id(supervisor.getId())
                .username(supervisor.getUser().getUsername())
                .firstName(supervisor.getUser().getFirstName())
                .lastName(supervisor.getUser().getLastName())
                .department(supervisor.getDepartment())
                .designation(supervisor.getDesignation())
                .build();
    }
}