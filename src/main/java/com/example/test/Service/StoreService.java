package com.example.test.Service;

import com.example.test.Model.*;
import com.example.test.Repository.CartRepo;
import com.example.test.Repository.OrdersRepo;
import com.example.test.Repository.ProductRepo;
import com.example.test.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class StoreService {

    @Autowired
    ProductRepo pro;

    @Autowired
    UserRepo userRepo;
    @Autowired
    CartRepo cartRepo;

    @Autowired
    OrdersRepo ordersRepo;


    public ResponseEntity<?> addToCart(Cart cart){
        cartRepo.save(cart);

        return ResponseEntity.ok("Product added successfully");
    }


    public ResponseEntity<?> addProduct(Product product){
        pro.save(product);
        return ResponseEntity.ok("Product added Successfully");
    }

    public ResponseEntity<?>checkout(int userid){
        Users user= userRepo.findById(userid).orElseThrow(()-> new UsernameNotFoundException("No User Found"));
        List<Cart>c = cartRepo.findByUser(user);

        if(c.isEmpty()) throw new UsernameNotFoundException("No products under this user id");

        Orders order = new Orders();
        order.setCreatedAt(LocalDate.now());
        order.setUser(user);

        List<OrderItem> orderItems = new ArrayList<>();
        for(Cart dum:c){
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(dum.getProduct());
            orderItem.setPrice(dum.getPrice());
            orderItem.setQuanitity(dum.getQuantity());
            orderItems.add(orderItem);
        }
        int price =0;
        for(OrderItem o:orderItems){
            price+=o.getPrice()*o.getQuanitity();
        }
        order.setTotalAmount(BigDecimal.valueOf(price));
        order.setItemList(orderItems);

        ordersRepo.save(order);
        cartRepo.deleteAll(c);

        return new ResponseEntity<>("Done", HttpStatus.OK);
    }
}
