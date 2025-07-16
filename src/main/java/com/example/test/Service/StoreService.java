package com.example.test.Service;

import com.example.test.Exception.NoProductFoundinUserCart;
import com.example.test.Exception.OutofStock;
import com.example.test.Exception.ProductNotFound;
import com.example.test.Model.Cart;
import com.example.test.Model.Orders;
import com.example.test.Model.OrderItem;
import com.example.test.Model.OrdersResponseDTO;
import com.example.test.Model.Product;
import com.example.test.Model.Users;
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
import java.util.Collections;
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
    @Autowired
    private ProductRepo productRepo;

    public ResponseEntity<String> addToCart(int userid, int productid) {
        Users user = userRepo.findById(userid).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Product product = productRepo.findById(productid).orElseThrow(() -> new ProductNotFound("No Product Found"));
        if (!product.isInStock()) throw new OutofStock("The Product is out of stock");
        System.out.println(product.toString());
        Cart cart = new Cart();
        cart.setUser(user);
        cart.setProduct(product);
        cart.setPrice(product.getProductPrice());
        cart.setQuantity(1);
        List<Cart> check = cartRepo.findByUser(user);
        if(check.isEmpty()){}
        else for(Cart c:check){
            if(c.getProduct().getProductId()==productid){
                c.setQuantity(c.getQuantity()+1);
                cartRepo.save(c);
                return ResponseEntity.ok("Product added successfully");
            }

        }
        cartRepo.save(cart);
        return ResponseEntity.ok("Product added successfully");
    }

    public ResponseEntity<String> addProduct(Product product) {

        pro.save(product);
        return ResponseEntity.ok("Product added Successfully");
    }

    public ResponseEntity<String> checkout(int userid) {

        Users user = userRepo.findById(userid).orElseThrow(() -> new UsernameNotFoundException("No User Found"));
        List<Cart> c = cartRepo.findByUser(user);
        if (c.isEmpty()) throw new NoProductFoundinUserCart("No products under this user id");

        Orders order = new Orders();
        order.setCreatedAt(LocalDate.now());
        order.setUser(user);
        List<OrderItem> orderItems = new ArrayList<>();
        for (Cart dum : c) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(dum.getProduct());
            orderItem.setPrice(dum.getPrice());
            orderItem.setQuanitity(dum.getQuantity());
            orderItems.add(orderItem);
        }
        int price = 0;
        for (OrderItem o : orderItems) {
            price += o.getPrice() * o.getQuanitity();
        }
        order.setTotalAmount(BigDecimal.valueOf(price));
        order.setItemList(orderItems);
        ordersRepo.save(order);
        cartRepo.deleteAll(c);
        return new ResponseEntity<>("Done", HttpStatus.OK);
    }

    public ResponseEntity<List<Product>> searchproduct(String name) {
        List<Product> searchresult = productRepo.findByProductName(name);
        if (searchresult.isEmpty()) throw new ProductNotFound("No Relevent Search Found");
        
        return new ResponseEntity<>(searchresult, HttpStatus.OK);

    }

    public ResponseEntity<List<Cart>> viewCart(int userid) {
        Users user = userRepo.findById(userid).orElseThrow(() -> new UsernameNotFoundException("No such user Found."));
        List<Cart> cartlList = cartRepo.findByUser(user);
        if (cartlList.isEmpty()) return new ResponseEntity("Cart is empty", HttpStatus.OK);
        else return new ResponseEntity<>(cartlList, HttpStatus.OK);
    }

    public ResponseEntity<?> updateCart(int userid, int productid, int quantity) {
        Users user = userRepo.findById(userid).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Product product = productRepo.findById(productid).orElseThrow(() -> new ProductNotFound("No Product Found"));
        Cart cart = cartRepo.findByProductAndUser(product, user);
        if (cart == null) throw new NoProductFoundinUserCart(user.getUsername() + ", no " + product.getProductName() + " is found in the Cart");
        cart.setQuantity(quantity);
        cartRepo.save(cart);
        return ResponseEntity.ok(Collections.singletonMap("message", "Quantity updated"));

    }

    public ResponseEntity<List<OrdersResponseDTO>> orderDetails(int userid) {
        Users user = userRepo.findById(userid).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        List<Orders> orders = ordersRepo.findByUser(user);
        if (orders.isEmpty()) return new ResponseEntity("No orders found", HttpStatus.NOT_FOUND);
        List<OrdersResponseDTO> output = new ArrayList<>();
        for (Orders o : orders) {
            OrdersResponseDTO op = new OrdersResponseDTO();
            op.setDate(o.getCreatedAt());
            op.setPrice(o.getTotalAmount());
            op.setProductname(o.getItemList()); // This will return OrderItem objects, likely needs to be mapped to product names
            output.add(op);

        }

        return new ResponseEntity<>(output, HttpStatus.FOUND);
    }

    public ResponseEntity<String> updateProduct(int productid, boolean stock, int price) {
        Product product = productRepo.findById(productid).orElseThrow(() -> new ProductNotFound("No Such Product Found"));
        product.setInStock(stock);
        product.setProductPrice(price);
        productRepo.save(product);
        return new ResponseEntity<>("Product Successfully updated", HttpStatus.ACCEPTED);
    }

    public ResponseEntity<List<Product>> getProduct() {
        return new ResponseEntity<>(productRepo.findAll(),HttpStatus.OK);
    }

    public ResponseEntity<?> removeCart(int userid, int productid) {
        Users user = userRepo.findById(userid).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Product product = productRepo.findById(productid).orElseThrow(() -> new ProductNotFound("No Product Found"));
        Cart cart = cartRepo.findByProductAndUser(product,user);
        cartRepo.delete(cart);
            return new ResponseEntity<>("Product Removed",HttpStatus.OK);
    }
}