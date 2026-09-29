package com.example.product_service.application.service;


import com.example.product_service.adapter.out.persistence.ProductEntity;
import com.example.product_service.application.port.in.ProductInventoryUseCase;
import com.example.product_service.application.port.out.EventPort;
import com.example.product_service.application.port.out.ProductInventoryPort;
import com.example.product_service.domain.model.Product;
import com.example.product_service.event.EventStatus;
import com.example.product_service.event.InventoryDecreaseResult;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

//재고 차감 api로 재고 확인 코드
@Service
@AllArgsConstructor
public class ProductInventoryService implements ProductInventoryUseCase {

    private final ProductInventoryPort productInventoryPort;
    private final EventPort eventPort;


    @Override
    @Transactional
    public InventoryDecreaseResult decreaseStocks(Long productId, int quantity) {

        ProductEntity product = productInventoryPort.findById(productId);

        boolean success = productInventoryPort.decreaseInventory(
                productId,
                quantity
        );

        if (!success) {
            return InventoryDecreaseResult.fail(
                    "재고가 부족합니다."
            );
        }

        Product resultProduct = Product.create(
                product.getId(),
                product.getProductName(),
                product.getPrice(),
                product.getStocks(),
                product.getCategoryId()
        );

        return InventoryDecreaseResult.success(resultProduct);
    }

    @Override
    @Transactional
    public void increaseStocks(Long productId, int quantity) {
        boolean success = productInventoryPort.increaseInventory(productId, quantity);

        eventPort.save("INCREASE", EventStatus.INCREASE_SUCCESS);

        if (!success) {
            throw new IllegalStateException("재고 복구에 실패했습니다. productId=" + productId);
        }
    }

}
