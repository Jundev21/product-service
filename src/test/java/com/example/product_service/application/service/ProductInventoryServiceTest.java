package com.example.product_service.application.service;

import com.example.product_service.application.port.out.ProductInventoryPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProductInventoryServiceTest {

    @Mock
    private ProductInventoryPort productInventoryPort;

    @InjectMocks
    private ProductInventoryService productInventoryService;

    @Test
    @DisplayName("상품 재고를 정상적으로 차감한다")
    void decreaseStocks() {

        Long productId = 1L;
        int quantity = 3;

        given(
                productInventoryPort.decreaseInventory(
                        productId,
                        quantity
                )
        ).willReturn(true);

        productInventoryService.decreaseStocks(
                productId,
                quantity
        );

        verify(productInventoryPort)
                .decreaseInventory(
                        productId,
                        quantity
                );
    }

    @Test
    @DisplayName("재고가 부족하면 예외가 발생한다")
    void decreaseStocksFail() {
        Long productId = 1L;
        int quantity = 200;

        given(
                productInventoryPort.decreaseInventory(
                        productId,
                        quantity
                )
        ).willReturn(false);

        assertThatThrownBy(
                () -> productInventoryService.decreaseStocks(
                        productId,
                        quantity
                )
        )
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("차감 수량이 0 이하이면 예외가 발생한다")
    void decreaseStocksInvalidQuantity() {
        assertThatThrownBy(
                () -> productInventoryService.decreaseStocks(
                        1L,
                        0
                )
        )
                .isInstanceOf(IllegalArgumentException.class);
    }
}