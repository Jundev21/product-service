package com.example.product_service.event;


public record OrderCreatedEvent(
        Long goodsId,
        int quantity
) {
}