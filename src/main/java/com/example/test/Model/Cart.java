package com.example.test.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int Cart_id;

    @ManyToOne
    private  Users user;

    @ManyToOne
    private  Product product;


    private int quantity;

    private int price;

     public Cart(Users user_id, Product product, int quantity) {

        this.user= user_id;
        this.product = product;
        this.quantity = quantity;
    }

    public Cart() {

    }
}
