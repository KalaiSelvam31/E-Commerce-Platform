package com.example.test.Repository;

import com.example.test.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
    @Query(value = "SELECT * FROM Product WHERE " +
            "LOWER(product_name) LIKE LOWER(CONCAT('%', :productName, '%')) " +
            "OR LOWER(product_name) LIKE LOWER(CONCAT('%', :productName)) " +
            "OR LOWER(product_name) LIKE LOWER(CONCAT(:productName, '%'))",
            nativeQuery = true)
    List<Product> findByProductName(String productName);
}