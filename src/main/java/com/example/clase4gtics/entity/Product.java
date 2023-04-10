package com.example.clase4gtics.entity;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name="Products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ProductID")
    private int id;

    @Column(nullable = false)
    private String productname;

    @ManyToOne
    @JoinColumn(name = "SupplierID")
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "CategoryID")
    private Category category;


    private String quantityperunit;
    private BigDecimal unitprice;
    private int unitsinstock;
    private int unitsonorder;
    private int reorderlevel;
    @Column(nullable = false)
    private boolean discontinued;

}
