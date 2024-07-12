package com.softwaveco.its.mapper;


import com.softwaveco.its.controller.request.SignupRequest;
import com.softwaveco.its.controller.response.UserDetailsResponse;
import com.softwaveco.its.data.entity.Role;
import com.softwaveco.its.data.entity.User;
import com.softwaveco.its.data.repository.RoleRepository;
import com.softwaveco.its.enums.UserRole;
import com.softwaveco.its.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Component
@RequiredArgsConstructor
public class UserMapper {
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;
    private final RoleRepository roleRepository;

    public User mapSignupRequestToUser(SignupRequest request) {
        String hashPassword = encoder.encode(request.getPassword());
        return User.builder()
                .username(request.getEmail())
                .password(hashPassword)
                .isVerified(false)
                .verificationToken(jwtUtils.generateVerificationToken(request.getEmail()))
                .roles(mapToRole(request.getRole()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .address(request.getAddress())
                .apartment(request.getApartment())
                .country(request.getCountry())
                .phone(request.getPhone())
                .state(request.getState())
                .city(request.getCity())
                .postcode(request.getPostcode())
                .civilNumber(request.getCivilNumber())
                .build();
    }

    private Set<Role> mapToRole(Set<UserRole> strings) {
        Set<Role> roles = new HashSet<>();
        strings.stream()
                .map(roleRepository::findByName)
                .forEach(role -> role.ifPresent(roles::add));
        return roles;
    }

    public UserDetailsResponse mapUserToUserDetailsResponse(User user) {
        Set<Role> roles = user.getRoles();
        List<String> rolesResponse = new ArrayList<>();
        for (Role role : roles) {
            String userRole = role.getName().name();
            rolesResponse.add(userRole);

        }
        return UserDetailsResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .isVerified(user.isVerified())
                .roles(rolesResponse)
                .build();
    }
}
