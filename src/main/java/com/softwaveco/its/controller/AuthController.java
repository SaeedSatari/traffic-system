package com.softwaveco.its.controller;

import com.softwaveco.its.controller.request.LoginRequest;
import com.softwaveco.its.controller.request.SignupRequest;
import com.softwaveco.its.controller.response.JwtResponse;
import com.softwaveco.its.controller.response.MessageResponse;
import com.softwaveco.its.exceptions.type.BadRequestException;
import com.softwaveco.its.mapper.UserMapper;
import com.softwaveco.its.security.AuthService;
import com.softwaveco.its.security.JwtUtils;
import com.softwaveco.its.security.TrafficUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;
    private final UserMapper userMapper;
    private final JwtUtils jwtUtils;


    @PostMapping("/login")
    public JwtResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            log.info("authenticateUser API...");
            Authentication authentication = authService.getAuthentication(loginRequest.getUsername(), loginRequest.getPassword());

            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.info("Generating access token...");
            String accessToken = jwtUtils.generateJwtToken(authentication);
            log.info("Access token generated...");
            TrafficUserDetails userDetails = authService.getUserDetails(authentication);

            List<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            return JwtResponse.builder()
                    .userId(userDetails.getId())
                    .accessToken(accessToken)
                    .roles(roles)
                    .username(userDetails.getUsername())
                    .type("Bearer")
                    .build();
        } catch (Exception e) {
            log.error("Username or password is incorrect");
            throw new BadRequestException("Username or password is incorrect",
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/signup")
    public MessageResponse registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        log.info("registerUser API...");
        authService.checkExistingUserAndSaveNewUser(userMapper.mapSignupRequestToUser(signUpRequest));
        return MessageResponse.builder()
                .message("User registered successfully!")
                .httpStatus(HttpStatus.CREATED)
                .build();
    }
}