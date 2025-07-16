package com.example.test.Repository;

import com.example.test.Model.Cart;
import com.example.test.Model.Product;
import com.example.test.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepo extends JpaRepository<Cart, Integer> {
    List<Cart> findByUser(Users user);
    Cart findByProductAndUser(Product product, Users user);
}