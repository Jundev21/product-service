package com.example.product_service.application.port.in;

public interface ProcessOrderInventoryUseCase {

    void process(
            String eventId,
            Long orderId,
            Long productId,
            int quantity
    );
}