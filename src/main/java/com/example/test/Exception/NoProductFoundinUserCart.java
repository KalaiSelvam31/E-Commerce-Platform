package com.example.test.Exception;

public class NoProductFoundinUserCart extends RuntimeException {
    public NoProductFoundinUserCart(String message) {
        super(message);
    }
}
