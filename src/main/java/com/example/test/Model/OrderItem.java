package com.example.test.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int OrderItemId;

    @ManyToOne
    private Product product;

    @ManyToOne
    @JsonBackReference
    private Orders order;

    private int quanitity;

    private int price;

}