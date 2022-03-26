package com.aop.cosmeticsonlinestore.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AuthRequest {
    @NotNull(message = "Email-ul nu trebuie sa lipseasca.")
    @NotBlank(message = "Email-ul nu trebuie sa lipseasca.")
    private String email;
    @NotNull(message = "Parola nu trebuie sa lipseasca.")
    @NotBlank(message = "Parola nu trebuie sa lipseasca.")
    private String password;
}
