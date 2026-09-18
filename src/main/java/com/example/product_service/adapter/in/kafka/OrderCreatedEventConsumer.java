package com.example.product_service.adapter.in.kafka;

import com.example.product_service.application.port.in.ProcessOrderInventoryUseCase;
import com.example.product_service.event.OrderCreatedEvent;
import com.example.product_service.event.PaymentFailedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderCreatedEventConsumer {

    private final ProcessOrderInventoryUseCase processOrderInventoryUseCase;

    @KafkaListener(
            topics = "order-created",
            groupId = "product-service"
    )
    public void consume(OrderCreatedEvent event) {

        processOrderInventoryUseCase.process(
                event.eventId(),
                event.orderId(),
                event.goodsId(),
                event.quantity()
        );
    }

    @KafkaListener(
            topics = "increase-inventory",
            groupId = "product-service"
    )
    public void consume(PaymentFailedEvent event) {
        System.out.println("PaymentFailedEvent: " + event);
        processOrderInventoryUseCase.failedProcess(
                event.orderId(),
                event.goodsId(),
                event.quantity()
        );
    }
}