package com.example.product_service.event;

public record InventoryDecreasedEvent(
        String eventId,
        Long orderId,
        Long goodsId,
        int quantity
) {
}