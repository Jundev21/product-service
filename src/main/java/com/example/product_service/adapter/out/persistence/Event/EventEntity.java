package com.example.product_service.adapter.out.persistence.Event;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@Table(name="events")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String eventType;
    private String eventId;

    private EventEntity(
            String eventType,
            String eventId
    ) {
        this.eventType = eventType;
        this.eventId = eventId;
    }

    public static EventEntity create(
            String eventType,
            String eventId
    ) {
        return new EventEntity(eventType, eventId);
    }
}
