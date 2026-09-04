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
    public void decreaseInventory(Long productId, int quantity) {
        int updatedRows = productRepository.decreaseInventory(productId, quantity);

        if (updatedRows != 1) {
            throw new IllegalStateException("상품이 없거나 재고가 부족합니다.");
        }
    }
    @Override
    public ProductEntity findById(Long productId) {
        return productRepository.findById(productId).orElseThrow(
                () -> new IllegalArgumentException("해당 상품이 없습니다.")
        );
    }


}