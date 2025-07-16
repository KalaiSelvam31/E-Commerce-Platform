package com.example.test.Service;

import com.example.test.Exception.ProductNotFound;
import com.example.test.Model.Cart;
import com.example.test.Model.Product;
import com.example.test.Model.Users;
import com.example.test.Repository.CartRepo;
import com.example.test.Repository.ProductRepo;
import com.example.test.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    ProductRepo pro;

    @Autowired
    UserRepo userRepo;
    @Autowired
    CartRepo cartRepo;



}
