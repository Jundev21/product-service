package com.example.product_service.event;


public record OrderCreatedEvent(
        String eventId,
        Long orderId,
        Long goodsId,
        int quantity
) {
}