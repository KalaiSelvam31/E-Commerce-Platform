package com.example.test.Model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReviewDTO {

    @NotNull(message = "Please write the review before post.")
    private String text;
    private int rating;
}
