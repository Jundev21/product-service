package com.example.product_service.adapter.out.persistence;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    @DisplayName("재고를 조건부로 차감한다")
    @Transactional
    void decreaseInventory() {

        ProductEntity product = new ProductEntity(
                null,
                "MacBook Pro",
                2500000,
                100
        );

        ProductEntity saved =
                productRepository.saveAndFlush(product);

        int affectedRows =
                productRepository.decreaseInventory(
                        saved.getId(),
                        3
                );

        ProductEntity result =
                productRepository.findById(saved.getId())
                        .orElseThrow();

        assertThat(affectedRows).isEqualTo(1);
        assertThat(result.getStocks()).isEqualTo(97);
    }
}