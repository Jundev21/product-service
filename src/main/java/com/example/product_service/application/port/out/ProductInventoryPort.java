package com.example.product_service.application.port.out;

import com.example.product_service.adapter.out.persistence.ProductEntity;
import com.example.product_service.domain.model.Product;

public interface ProductInventoryPort {
    void decreaseInventory(
            Long productId,
            int quantity
    );

    ProductEntity findById(Long productId);

}
