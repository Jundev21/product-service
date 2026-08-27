package com.example.product_service.application.port.in;

public interface ProductInventoryUseCase {
    void decreaseStocks(Long productId, int quantity);

    void increaseStocks(Long productId, int quantity);
}
