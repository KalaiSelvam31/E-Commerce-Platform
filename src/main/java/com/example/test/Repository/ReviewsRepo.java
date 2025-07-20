package com.example.test.Repository;

import com.example.test.Model.Orders;
import com.example.test.Model.Reviews;
import jakarta.persistence.criteria.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewsRepo extends JpaRepository<Reviews,Integer> {
    Optional<Reviews> findByOrders(Orders orders);

    Optional<List<Reviews>> findByProductid(int productid);
}
