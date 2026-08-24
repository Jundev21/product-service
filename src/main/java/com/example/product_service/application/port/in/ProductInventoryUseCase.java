package com.example.product_service.application.port.in;

import com.example.product_service.domain.model.Product;

public interface ProductInventoryUseCase {
    void decreaseStocks(int stocks);

    void increaseStocks(int stocks);
}
