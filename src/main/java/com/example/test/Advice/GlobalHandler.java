package com.example.test.Advice;

import com.example.test.Exception.ProductNotFound;
import com.example.test.Model.Error;
import com.example.test.Model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;

@RestControllerAdvice
public class GlobalHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> MethodArgumentNotvalid(MethodArgumentNotValidException e){
        Error er = new Error(e.getBindingResult().getFieldError().getDefaultMessage()
                ,e.getStatusCode().toString(), LocalDate.now());
        return new ResponseEntity<>(er, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ProductNotFound.class)
    public ResponseEntity<Error> ProductNotFound(ProductNotFound e){
        Error er = new Error(e.toString()
                ,HttpStatus.NOT_FOUND.toString(), LocalDate.now());
        return new ResponseEntity<>(er, HttpStatus.BAD_REQUEST);
    }
}
