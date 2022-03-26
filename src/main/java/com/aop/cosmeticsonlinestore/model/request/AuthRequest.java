package com.aop.cosmeticsonlinestore.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AuthRequest {
    @NotNull(message = "Username-ul nu trebuie sa lipseasca.")
    @NotBlank(message = "Username-ul nu trebuie sa lipseasca.")
    private String username;
    @NotNull(message = "Parola nu trebuie sa lipseasca.")
    @NotBlank(message = "Parola nu trebuie sa lipseasca.")
    private String password;
}
