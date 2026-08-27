package com.example.product_service.adapter.in.web.response;

import jakarta.validation.constraints.Positive;

public record DecreaseInventoryRequest(
        @Positive
        int quantity
) {
}