package com.example.product_service.application.service;

import com.example.product_service.application.port.in.ProcessOrderInventoryUseCase;
import com.example.product_service.application.port.in.ProductInventoryUseCase;
import com.example.product_service.application.port.out.EventPort;
import com.example.product_service.application.port.out.InventoryEventPort;
import com.example.product_service.event.EventStatus;
import com.example.product_service.event.InventoryDecreaseFailedEvent;
import com.example.product_service.event.InventoryDecreaseResult;
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

        if (eventPort.existsByEventId(eventId)) {
            return;
        }

        InventoryDecreaseResult result = productInventoryUseCase.decreaseStocks(productId, quantity);

        if (!result.success()) {

            eventPort.save(eventId, EventStatus.DECREASE_FAILED);

            inventoryEventPort.publishDecreaseFailed(
                    new InventoryDecreaseFailedEvent(eventId, orderId, productId, quantity, result.reason())
            );

            return;
        }

        eventPort.save(eventId, EventStatus.DECREASE_SUCCESS);

        inventoryEventPort.publishDecreased(
                new InventoryDecreasedEvent(
                        eventId,
                        orderId,
                        productId,
                        quantity,
                        result.product().getPrice()
                )
        );
    }

    @Override
    public void failedProcess(Long orderId, Long productId, int quantity) {
        productInventoryUseCase.increaseStocks(productId, quantity);
    }
}