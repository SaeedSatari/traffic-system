package com.softwaveco.its.controller;

import com.softwaveco.its.controller.response.UserDetailsResponse;
import com.softwaveco.its.data.entity.User;
import com.softwaveco.its.mapper.UserMapper;
import com.softwaveco.its.service.UserService;
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
@RequestMapping(path = "/api/v1/users")
@SecurityRequirement(name = "bearerAuth")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "get list users")
    public List<UserDetailsResponse> getUserList() {
        log.info("getUserList API...");
        List<User> users = userService.listUsers();
        return users.stream()
                .map(userMapper::mapUserToUserDetailsResponse)
                .collect(Collectors.toList());
    }
}
