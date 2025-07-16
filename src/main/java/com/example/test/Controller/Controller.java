package com.example.test.Controller;

import com.example.test.Exception.ProductNotFound;
import com.example.test.Model.Cart;
import com.example.test.Model.CartDto;
import com.example.test.Model.Product;
import com.example.test.Model.Users;
import com.example.test.Repository.CartRepo;
import com.example.test.Repository.ProductRepo;
import com.example.test.Repository.UserRepo;
import com.example.test.Service.StoreService;
import com.example.test.Service.JwtService;
import com.example.test.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")

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

    @Autowired
    CartRepo cartRepo;

    @PostMapping("/Register")
    public ResponseEntity<?> register(@RequestBody @Valid Users user) {
        String message = u_service.register(user);
        return ResponseEntity.ok(Collections.singletonMap("message", message));
    }


    @PostMapping("/Login")
    public ResponseEntity<?> login(@RequestBody Users users) {
        try {
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    users.getUsername(), users.getPassword()
            );
            Authentication authenticated = u_service.authenticate(authentication);

            Users user = u_service.getUserByUsername(users.getUsername());
            String token = jwtService.generateToken(user);

            Map<String, Object> response = new HashMap<>();
            response.put("Token", token);
            response.put("userId", user.getId());
            response.put("role", user.getRole()); // optional, useful for role-based UI

            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @GetMapping("getProduct")
    public ResponseEntity<List<Product>>getProduct(){
        return storeService.getProduct();
    }

    @PostMapping("/addToCart/{userid}/{productid}")
    public ResponseEntity<?> addToCart(@PathVariable int userid, @PathVariable int productid) {
        return storeService.addToCart(userid, productid);
    }
    @PostMapping("/removeCart/{userid}/{productid}")
    public ResponseEntity<?> removeCart(@PathVariable int userid, @PathVariable int productid) {
        return storeService.removeCart(userid, productid);
    }

    @PreAuthorize("hasRole('Admin')")
    @PostMapping("/addProducts")
    public ResponseEntity<?> addProduct(@RequestBody Product pro) {
        return storeService.addProduct(pro);
    }

    @PreAuthorize("hasRole('Admin')")
    @PostMapping("/updateProducts/{productid}/{bool}/{price}")
    public ResponseEntity<?> updateProduct(@PathVariable int productid, @PathVariable boolean bool, @PathVariable int price) {
        return storeService.updateProduct(productid, bool, price);
    }

    @PostMapping("/Checkout/{userid}")
    public ResponseEntity<?> checkOut(@PathVariable int userid) {
        return storeService.checkout(userid);
    }

    @GetMapping("/Search/{productname}")
    public ResponseEntity<?> searchproduct(@PathVariable String productname) {
        return storeService.searchproduct(productname);
    }

    @GetMapping("/viewCart/{userid}")
    public ResponseEntity<?> viewCart(@PathVariable int userid) {
        return storeService.viewCart(userid);
    }

    @PostMapping("/updateCart/{userid}/{productid}/{quantity}")
    public ResponseEntity<?> updateCart(@PathVariable int userid, @PathVariable int productid, @PathVariable int quantity) {
        return storeService.updateCart(userid, productid, quantity);
    }

    @GetMapping("/orderDetails/{userid}")
    public ResponseEntity<?> orderDetails(@PathVariable int userid) {
        return storeService.orderDetails(userid);
    }
}
