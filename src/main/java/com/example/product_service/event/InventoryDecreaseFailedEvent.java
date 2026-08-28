package com.example.product_service.event;

public record InventoryDecreaseFailedEvent(
        Long orderId,
        Long goodsId,
        int quantity,
        String reason
) {
}