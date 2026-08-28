package com.example.product_service.event;

public record InventoryDecreasedEvent(
        Long orderId,
        Long goodsId,
        int quantity
) {
}