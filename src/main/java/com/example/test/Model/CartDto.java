package com.example.test.Model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class CartDto {

    private int user_id;
    private int product;
    private int quantity;
}