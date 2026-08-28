package com.example.product_service.adapter.out.persistence;

import com.example.product_service.application.port.out.ProductInventoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductInventoryAdapter implements ProductInventoryPort {

    private final ProductRepository productRepository;

    //재고 차감할때 비관적락을 사용해서 데이터 정합성을 맞게한다.
    @Override
    public boolean decreaseInventory(Long productId, int quantity) {
        return productRepository.decreaseInventory(
                productId, quantity
        ) == 1;
    }

    @Override
    public boolean existsById(Long productId) {
        return productRepository.existsById(productId);
    }
}