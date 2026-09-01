package com.example.product_service.adapter.out.persistence.Event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long> {
    Boolean existsByEventId(String eventId);
}
