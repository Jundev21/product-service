package com.example.product_service.application.service;


import com.example.product_service.adapter.out.persistence.ProductEntity;
import com.example.product_service.application.port.in.ProductInventoryUseCase;
import com.example.product_service.application.port.out.ProductInventoryPort;
import com.example.product_service.domain.model.Product;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

//재고 차감 api로 재고 확인 코드
@Service
@AllArgsConstructor
public class ProductInventoryService implements ProductInventoryUseCase {

    private final ProductInventoryPort productInventoryPort;

    @Override
    @Transactional
    public Product decreaseStocks(Long productId, int quantity) {

        ProductEntity findProducts = productInventoryPort.findById(productId);
        productInventoryPort.decreaseInventory(productId, quantity);

        return Product.create(
                findProducts.getProductName(),
                findProducts.getPrice(),
                findProducts.getStocks()
        );
    }

    @Override
    public void increaseStocks(Long productId, int quantity) {

    }

}
