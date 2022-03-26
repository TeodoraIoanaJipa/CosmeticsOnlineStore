package com.aop.cosmeticsonlinestore.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "store_user")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    private String firstName;

    private String lastName;

    /**
     * activation code is sent email.
     */
    private String activationCode;

    private boolean active;

    @NotNull
    private String email;

    private String password;
}
