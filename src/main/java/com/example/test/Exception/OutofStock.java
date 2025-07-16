package com.example.test.Exception;

public class OutofStock extends RuntimeException {
    public OutofStock(String message) {
        super(message);
    }
}
