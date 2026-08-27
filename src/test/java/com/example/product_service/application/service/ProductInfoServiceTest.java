package com.example.product_service.application.service;

import com.example.product_service.application.port.out.ProductInfoPort;
import com.example.product_service.domain.model.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProductInfoServiceTest {

    @Mock
    private ProductInfoPort productInfoPort;

    @InjectMocks
    ProductInfoService productInfoService;

    @Test
    @DisplayName("새로운 상품 등록")
    void addNewProduct() {

        Product savedProduct = new Product(
                "MacBook Pro",
                2500000,
                1L,
                100
        );

        given(productInfoPort.save(
                        org.mockito.ArgumentMatchers.any(Product.class)
                )
        ).willReturn(savedProduct);

        Product result = productInfoService.addNewProduct(
                "MacBook Pro",
                2500000,
                100
        );

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getProductName()).isEqualTo("MacBook Pro");
        assertThat(result.getPrice()).isEqualTo(2500000);
        assertThat(result.getProductStocks()).isEqualTo(100);

        verify(productInfoPort)
                .save(org.mockito.ArgumentMatchers.any(Product.class));
    }

    @Test
    @DisplayName("상품 조회")
    void searchProduct() {

        Product product = new Product(
                "MacBook Pro",
                2500000,
                1L,
                100
        );

        given(productInfoPort.searchProductDetail(1L))
                .willReturn(product);

        // when
        Product result =
                productInfoService.productDetails(1L);

        // then
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getProductName())
                .isEqualTo("MacBook Pro");

        verify(productInfoPort).searchProductDetail(1L);
    }

    @Test
    void productLists() {
    }

}