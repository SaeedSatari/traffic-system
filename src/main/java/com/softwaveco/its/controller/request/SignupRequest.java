package com.softwaveco.its.controller.request;

import com.softwaveco.its.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
public class SignupRequest {
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private Set<UserRole> role;

    @NotBlank
    @Size(min = 8, max = 20)
    private String password;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private String address;

    private String apartment;

    private String country;

    private String phone;

    private String state;

    private String city;

    private String postcode;

    private String civilNumber;
}