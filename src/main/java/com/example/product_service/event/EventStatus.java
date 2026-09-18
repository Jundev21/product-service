package com.example.product_service.event;

import lombok.Getter;

@Getter
public enum EventStatus {
    DECREASE_SUCCESS("decrease-success"),
    DECREASE_FAILED("decrease-failed"),
    INCREASE_SUCCESS("increase-success");

    private final String value;

    EventStatus(String value) {
        this.value = value;
    }

}