package com.example.product_service.application.port.out;

import com.example.product_service.adapter.out.persistence.Event.EventEntity;
import com.example.product_service.event.EventStatus;

public interface EventPort {
    boolean existsByEventId(String eventId);
    EventEntity save(String eventId, EventStatus eventType);
}
