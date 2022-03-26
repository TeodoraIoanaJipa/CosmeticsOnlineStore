package com.aop.cosmeticsonlinestore.model;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Prenumele este obligatoriu")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Numele este obligatoriu")
    @Column(name = "last_name")
    private String lastName;

    @NotBlank(message = "Numărul de telefon este obligatoriu")
    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "order_date")
    private Date orderDate;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
}
