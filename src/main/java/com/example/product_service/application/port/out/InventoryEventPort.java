package com.example.product_service.application.port.out;

import com.example.product_service.event.InventoryDecreaseFailedEvent;
import com.example.product_service.event.InventoryDecreasedEvent;

public interface InventoryEventPort {

    void publishDecreased(
            InventoryDecreasedEvent event
    );

    void publishDecreaseFailed(
            InventoryDecreaseFailedEvent event
    );
}