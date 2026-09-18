package com.example.product_service.event;

import com.example.product_service.domain.model.Product;

public record InventoryDecreaseResult(
        boolean success,
        Product product,
        String reason
) {

    public static InventoryDecreaseResult success(Product product) {
        return new InventoryDecreaseResult(
                true,
                product,
                null
        );
    }

    public static InventoryDecreaseResult fail(String reason) {
        return new InventoryDecreaseResult(
                false,
                null,
                reason
        );
    }
}