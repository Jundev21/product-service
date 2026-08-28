package com.example.product_service.application.service;


import com.example.product_service.application.port.in.ProductInventoryUseCase;
import com.example.product_service.application.port.out.ProductInventoryPort;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductInventoryService implements ProductInventoryUseCase {

    private final ProductInventoryPort productInventoryPort;

    @Override
    @Transactional
    public void decreaseStocks(
            Long productId,
            int quantity
    ) {

        if (quantity <= 0) {
            throw new IllegalArgumentException("차감 수량은 0보다 커야 합니다.");
        }

        boolean success =
                productInventoryPort.decreaseInventory(
                        productId,
                        quantity
                );

        if (!success) {
            throw new IllegalStateException("상품이 없거나 재고가 부족합니다.");
        }
    }

    @Override
    public void increaseStocks(Long productId, int quantity) {

    }
}
