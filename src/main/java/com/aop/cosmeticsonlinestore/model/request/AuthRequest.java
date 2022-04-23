package com.aop.cosmeticsonlinestore.model.request;

import lombok.Data;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Data
public class AuthRequest {
    @NotNull(message = "Username-ul nu trebuie sa lipseasca.")
    @NotBlank(message = "Username-ul nu trebuie sa lipseasca.")
    private String username;
    @NotNull(message = "Parola nu trebuie sa lipseasca.")
    @NotBlank(message = "Parola nu trebuie sa lipseasca.")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean hasValidFields() {
        return true;
    }

    Map<String, String> getErrors(BindingResult bindingResult) {
        Collector<FieldError, ?, Map<String, String>> collector = Collectors.toMap(
                fieldError -> fieldError.getField() + "Error",
                FieldError::getDefaultMessage
        );
        return bindingResult.getFieldErrors().stream().collect(collector);
    }

    public String validateData(BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = getErrors(bindingResult);

            model.mergeAttributes(errors);

            return "home/user/login";
        }
        return "/";
    }
}
