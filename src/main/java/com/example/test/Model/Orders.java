package com.example.test.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @ManyToOne
    private Users user;

    private BigDecimal totalAmount;

    private LocalDate createdAt;

    @OneToMany
    List<OrderItem> itemList;
}
