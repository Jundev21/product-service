package com.example.product_service.application.port.in;

import com.example.product_service.domain.model.Product;
import com.example.product_service.event.InventoryDecreaseResult;

public interface ProductInventoryUseCase {
    InventoryDecreaseResult decreaseStocks(Long productId, int quantity);
    void increaseStocks(Long productId, int quantity);
}
