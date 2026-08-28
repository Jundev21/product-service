package com.example.product_service.adapter.in.kafka;

import com.example.product_service.application.port.in.ProductInventoryUseCase;
import com.example.product_service.event.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderCreatedEventConsumer {

    private final ProductInventoryUseCase productInventoryUseCase;

    @KafkaListener(
            topics = "order-created",
            groupId = "product-service"
    )
    public void consume(OrderCreatedEvent event) {

        System.out.println(
                "OrderCreatedEvent 수신: " + event
        );

        productInventoryUseCase.decreaseStocks(
                event.goodsId(),
                event.quantity()
        );
    }
}