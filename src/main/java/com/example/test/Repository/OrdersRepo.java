package com.example.test.Repository;

import com.example.test.Model.Orders;
import com.example.test.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepo extends JpaRepository<Orders,Integer> {
    List<Orders> findByUser(Users user);
}
