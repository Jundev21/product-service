package com.example.product_service.adapter.out.persistence.Event;

import com.example.product_service.adapter.out.persistence.ProductRepository;
import com.example.product_service.application.port.out.EventPort;
import com.example.product_service.application.port.out.ProductInventoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EventAdapter implements EventPort {

    private final EventRepository eventRepository;

    @Override
    public boolean existsByEventId(String eventId) {
        return eventRepository.existsByEventId(eventId);
    }

    @Override
    public EventEntity save(String eventId, String eventType) {
        EventEntity eventEntity = EventEntity.create(eventType, eventId);
        return eventRepository.save(eventEntity);
    }
}