package com.example.product_service.adapter.out.kafka;

import com.example.product_service.application.port.out.InventoryEventPort;
import com.example.product_service.event.InventoryDecreaseFailedEvent;
import com.example.product_service.event.InventoryDecreasedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
@RequiredArgsConstructor
public class InventoryEventPublisher implements InventoryEventPort {

    private static final String DECREASED_TOPIC = "inventory-decreased";
    private static final String DECREASE_FAILED_TOPIC = "inventory-decrease-failed";

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public boolean isDuplicateEvent(String eventId) {
        return false;
    }

    @Override
    public void publishDecreased(InventoryDecreasedEvent event) {
        send(DECREASED_TOPIC, event.orderId().toString(), event);
    }

    @Override
    public void publishDecreaseFailed(InventoryDecreaseFailedEvent event) {
        send(DECREASE_FAILED_TOPIC, event.orderId().toString(), event);
    }

    private void send(String topic, String key, Object event) {
        try {
            kafkaTemplate.send(topic, key, event).get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Kafka 이벤트 발행 중 인터럽트 발생", e);
        } catch (ExecutionException e) {
            throw new IllegalStateException("Kafka 이벤트 발행 실패. topic=" + topic, e);
        }
    }
}
