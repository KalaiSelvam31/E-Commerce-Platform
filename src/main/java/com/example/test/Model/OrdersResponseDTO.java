package com.example.test.Model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class OrdersResponseDTO {
    private int orderid;
    private List<OrderItem> productname;
    private BigDecimal price;
    private LocalDate date;

}
