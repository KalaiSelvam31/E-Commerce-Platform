package com.example.test.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class OrderItem {

    @Id
    private int OrderItemId;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Orders order;

    private int quanitity;

    private int price;
}
