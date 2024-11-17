package com.company.bazar.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class SaleProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private  long idSaleProduct;
    @ManyToOne
    @JoinColumn (name = "cod_sale", nullable = false)
    @JsonBackReference
    private Sale sale;
    @ManyToOne
    @JoinColumn (name = "codProduct", nullable = false)
    @JsonBackReference
    private Product product;
    private int quantity;

}