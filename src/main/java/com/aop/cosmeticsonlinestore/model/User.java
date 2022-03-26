package com.aop.cosmeticsonlinestore.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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

    @NotBlank(message="email is required")
    private String email;

    private String password;
}
