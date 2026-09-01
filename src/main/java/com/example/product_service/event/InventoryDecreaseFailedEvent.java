package com.example.product_service.event;

public record InventoryDecreaseFailedEvent(
        String eventId,
        Long orderId,
        Long goodsId,
        int quantity,
        String reason
) {
}