package com.aop.cosmeticsonlinestore.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AuthRequest {
    @NotNull(message = "Username-ul nu trebuie sa lipseasca.")
    private String username;
    @NotNull
    private String password;
}
