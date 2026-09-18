package com.example.product_service.adapter.out.persistence.Event;

import com.example.product_service.event.EventStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name="events")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private EventStatus eventType;

    private String eventId;

    private EventEntity(
            EventStatus eventType,
            String eventId
    ) {
        this.eventType = eventType;
        this.eventId = eventId;
    }

    public static EventEntity create(
            EventStatus eventType,
            String eventId
    ) {
        return new EventEntity(eventType, eventId);
    }
}
