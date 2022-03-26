package com.aop.cosmeticsonlinestore.model.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RegistrationRequest {
    @Email
    @NotNull(message = "Emailul nu trebuie sa lipseasca.")
    private String email;

    @NotBlank(message = "Username-ul nu trebuie sa lipseasca.")
    private String username;

    @NotBlank(message = "Numele nu trebuie sa lipseasca.")
    private String firstName;

    @NotBlank(message = "Prenumele nu trebuie sa lipseasca.")
    private String lastName;

    @NotBlank(message = "Parola nu trebuie sa lipseasca.")
    private String password;

    @NotBlank
    private String rePassword;
}
