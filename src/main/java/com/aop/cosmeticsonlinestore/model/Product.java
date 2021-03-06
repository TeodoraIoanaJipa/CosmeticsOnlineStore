package com.aop.cosmeticsonlinestore.model;

import lombok.Getter;
import lombok.Setter;
//import org.hibernate.validator.constraints.Length;
import org.hibernate.annotations.NotFound;
import javax.validation.constraints.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(max = 50)
    @NotNull
    @Column(name = "title")
    private String title;

    @Size(max = 255)
    @Column(name = "description")
    private String description;

    @Size(max = 100)
    private String brand;

    @Size(max = 20)
    private String volume;

    @Column(name = "price")
    @NotNull
    private Float price;

    @Size(max = 255)
    @Column(name = "product_type")
    private String productType;

    private Integer year;

    @Size(max = 255)
    @Column(name = "product_base_notes")
    private String fragranceTopNotes;

    @Size(max = 255)
    @Column(name = "image_url")
    private String imageURL;
}
