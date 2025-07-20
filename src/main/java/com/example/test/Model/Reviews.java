package com.example.test.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reviewid;

    private int productid;
    private int rating;

    private String text;

    private String category;

    @OneToOne
    @JoinColumn(name="order_id",unique = true)
    private Orders orders;

}
