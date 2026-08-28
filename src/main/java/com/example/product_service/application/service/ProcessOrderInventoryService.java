package com.example.product_service.application.service;

import com.example.product_service.application.port.in.ProcessOrderInventoryUseCase;
import com.example.product_service.application.port.in.ProductInventoryUseCase;
import com.example.product_service.application.port.out.InventoryEventPort;
import com.example.product_service.event.InventoryDecreaseFailedEvent;
import com.example.product_service.event.InventoryDecreasedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProcessOrderInventoryService implements ProcessOrderInventoryUseCase {

    private final ProductInventoryUseCase productInventoryUseCase;
    private final InventoryEventPort inventoryEventPort;

    @Override
    public void process(
            Long orderId,
            Long productId,
            int quantity
    ) {

        try {

            productInventoryUseCase.decreaseStocks(
                    productId,
                    quantity
            );

            inventoryEventPort.publishDecreased(
                    new InventoryDecreasedEvent(
                            orderId,
                            productId,
                            quantity
                    )
            );

        } catch (IllegalStateException e) {
            inventoryEventPort.publishDecreaseFailed(
                    new InventoryDecreaseFailedEvent(
                            orderId,
                            productId,
                            quantity,
                            e.getMessage()
                    )
            );
        }
    }
}