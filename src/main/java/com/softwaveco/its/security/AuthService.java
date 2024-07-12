package com.softwaveco.its.security;


import com.softwaveco.its.data.entity.User;
import com.softwaveco.its.data.repository.UserRepository;
import com.softwaveco.its.exceptions.type.BadRequestException;
import com.softwaveco.its.exceptions.type.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;


    public Authentication getAuthentication(String username, String password) {
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

    public TrafficUserDetails getUserDetails(Authentication authentication) {
        return (TrafficUserDetails) authentication.getPrincipal();
    }

    public void checkExistingUserAndSaveNewUser(User user) {
        if (Boolean.TRUE.equals(userRepository.existsByUsername(user.getUsername()))) {
            throw new BadRequestException("Email is already in use!", HttpStatus.BAD_REQUEST);
        }
        try {
            userRepository.save(user);
        } catch (Exception e) {
            log.error("Error in saveUser: {}", e.getMessage());
            throw new ServiceException("Error in saveUser: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}