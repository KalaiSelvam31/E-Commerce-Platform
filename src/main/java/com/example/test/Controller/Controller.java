package com.example.test.Controller;
import com.example.test.Exception.ProductNotFound;
import com.example.test.Model.Cart;
import com.example.test.Model.CartDto;
import com.example.test.Model.Product;
import com.example.test.Model.Users;
import com.example.test.Repository.ProductRepo;
import com.example.test.Repository.UserRepo;
import com.example.test.Service.StoreService;
import com.example.test.Service.JwtService;
import com.example.test.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;


import java.util.Collections;

@RestController
public class Controller {

    @Autowired
    UserService u_service;

    @Autowired
    private JwtService jwtService;

    @Autowired
    StoreService storeService;

    @Autowired
    UserRepo userRepo;
    @Autowired
    ProductRepo productRepo;


    @PostMapping("/Register")
    public String register(@RequestBody @Valid  Users user) {

        return u_service.register(user);
    }

    @PostMapping("/Login")
    public ResponseEntity<?> login(@RequestBody Users users) {
        try{
            Authentication authentication = new UsernamePasswordAuthenticationToken(users.getUsername(),users.getPassword());
            Authentication authenticated = u_service.authenticate(authentication);

            Users user =u_service.getUserByUsername(users.getUsername());

            String token = jwtService.generateToken(user);

            return ResponseEntity.ok().body(Collections.singletonMap("Token",token));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @PostMapping("/addToCart")
    public ResponseEntity<?> addToCart(@RequestBody CartDto dto){
        Users users= userRepo.findById(dto.getUser_id()).orElseThrow(()->new UsernameNotFoundException("No such User Found"));
        Product product = productRepo.findById(dto.getProduct()).orElseThrow(()->new ProductNotFound("No such Product is found"));
        Cart c = new Cart(users,product,dto.getQuantity());
        return storeService.addToCart(c);

    }
    @PostMapping("/addProducts")
    public ResponseEntity<?> addProduct(@RequestBody Product pro){
            return storeService.addProduct(pro);
    }

    @GetMapping("/Checkout/{userid}")
    public ResponseEntity<?> checkOut(@PathVariable int userid){
        return storeService.checkout(userid);

    }
}
