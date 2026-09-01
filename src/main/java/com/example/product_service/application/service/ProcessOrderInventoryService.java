package com.example.product_service.application.service;

import com.example.product_service.adapter.out.persistence.Event.EventEntity;
import com.example.product_service.application.port.in.ProcessOrderInventoryUseCase;
import com.example.product_service.application.port.in.ProductInventoryUseCase;
import com.example.product_service.application.port.out.EventPort;
import com.example.product_service.application.port.out.InventoryEventPort;
import com.example.product_service.event.InventoryDecreaseFailedEvent;
import com.example.product_service.event.InventoryDecreasedEvent;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProcessOrderInventoryService implements ProcessOrderInventoryUseCase {

    private final ProductInventoryUseCase productInventoryUseCase;
    private final InventoryEventPort inventoryEventPort;
    private final EventPort eventPort;

    @Override
    @Transactional
    public void process(
            String eventId,
            Long orderId,
            Long productId,
            int quantity
    ) {

        if (eventPort.existsByEventId(eventId)) return;

        try {
            productInventoryUseCase.decreaseStocks(productId, quantity);
            eventPort.save(eventId,"decrease-stocks");
            inventoryEventPort.publishDecreased(
                    new InventoryDecreasedEvent(
                            eventId,
                            orderId,
                            productId,
                            quantity
                    )
            );

        } catch (IllegalStateException e) {

            eventPort.save(eventId,"decrease-failed");
            inventoryEventPort.publishDecreaseFailed(
                    new InventoryDecreaseFailedEvent(
                            eventId,
                            orderId,
                            productId,
                            quantity,
                            e.getMessage()
                    )
            );
        }
    }
}