package com.aop.cosmeticsonlinestore.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "addresses")
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message="Strada este obligatorie")
    @Column(name = "street")
    private String street;

    @NotBlank(message="Orașul este obligatorie")
    @Column(name = "city")
    private String city;

    @NotBlank(message="Județul este obligatorie")
    @Column(name = "county")
    private String county;

    @NotBlank(message="Codul poștal este obligatorie")
    @Column(name = "postal_code")
    private String postalCode;
}
