package com.aop.cosmeticsonlinestore.model.request;

import com.aop.cosmeticsonlinestore.model.Address;
import com.aop.cosmeticsonlinestore.model.User;
import lombok.Data;
import org.springframework.core.annotation.Order;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class OrderRequest {
    @NotBlank(message = "Prenumele este obligatoriu")
    private String firstName;

    @NotBlank(message = "Numele este obligatoriu")
    private String lastName;

    @NotBlank(message = "Numărul de telefon este obligatoriu")
    private String phoneNumber;

    @NotBlank(message="Strada este obligatorie")
    private String street;

    @NotBlank(message="Orașul este obligatorie")
    private String city;

    @NotBlank(message="Județul este obligatorie")
    private String county;

    @NotBlank(message="Codul poștal este obligatorie")
    private String postalCode;
}
