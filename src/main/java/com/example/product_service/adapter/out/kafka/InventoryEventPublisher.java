package com.example.product_service.adapter.out.kafka;

import com.example.product_service.application.port.out.InventoryEventPort;
import com.example.product_service.event.InventoryDecreaseFailedEvent;
import com.example.product_service.event.InventoryDecreasedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InventoryEventPublisher implements InventoryEventPort {

    private static final String DECREASED_TOPIC = "inventory-decreased";

    private static final String DECREASE_FAILED_TOPIC = "inventory-decrease-failed";

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void publishDecreased(InventoryDecreasedEvent event) {
        kafkaTemplate.send(
                DECREASED_TOPIC,
                event.orderId().toString(),
                event
        );
    }

    @Override
    public void publishDecreaseFailed(InventoryDecreaseFailedEvent event) {
        kafkaTemplate.send(
                DECREASE_FAILED_TOPIC,
                event.orderId().toString(),
                event
        );
    }
}