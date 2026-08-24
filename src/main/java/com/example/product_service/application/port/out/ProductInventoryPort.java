package com.example.product_service.application.port.out;

public interface ProductInventoryPort {
    boolean decreaseInventory(
            Long productId,
            int quantity
    );
}
