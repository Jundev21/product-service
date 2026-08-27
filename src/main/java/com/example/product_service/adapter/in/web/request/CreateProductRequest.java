package com.example.product_service.adapter.in.web.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record CreateProductRequest(

        @NotBlank
        String productName,

        @Positive
        int price,

        @PositiveOrZero
        int stocks
) {
}