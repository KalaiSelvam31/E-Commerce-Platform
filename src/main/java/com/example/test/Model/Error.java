package com.example.test.Model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Data
public class Error {

    private String error;
    private String status;
    private LocalDate localDate;

    public Error(String error, String status, LocalDate localDate) {
        this.error = error;
        this.status = status;
        this.localDate = localDate;
    }
}
