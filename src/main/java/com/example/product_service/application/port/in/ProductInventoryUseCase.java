package com.example.product_service.application.port.in;

import com.example.product_service.domain.model.Product;

public interface ProductInventoryUseCase {
    Product decreaseStocks(Long productId, int quantity);

    void increaseStocks(Long productId, int quantity);
}
