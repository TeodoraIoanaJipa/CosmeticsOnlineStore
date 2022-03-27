package com.aop.cosmeticsonlinestore.model.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RegistrationRequest {
    @Email
    @NotBlank(message = "Emailul nu trebuie sa lipseasca.")
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }
}
