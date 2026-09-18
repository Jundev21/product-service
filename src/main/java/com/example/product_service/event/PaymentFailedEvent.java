package com.example.product_service.event;

import lombok.Getter;
public record PaymentFailedEvent(
        Long orderId,
        Long goodsId,
        Long amount,
        int quantity,
        String reason
) {
}