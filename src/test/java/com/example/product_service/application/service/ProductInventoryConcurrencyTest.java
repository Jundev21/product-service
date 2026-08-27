package com.example.product_service.application.service;

import com.example.product_service.adapter.out.persistence.ProductEntity;
import com.example.product_service.adapter.out.persistence.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ProductInventoryConcurrencyTest {

    @Autowired
    private ProductInventoryService productInventoryService;

    @Autowired
    private ProductRepository productRepository;

    private Long productId;

    @BeforeEach
    void setUp() {

        productRepository.deleteAll();

        ProductEntity product = new ProductEntity(
                null,
                "MacBook Pro",
                2500000,
                100
        );

        ProductEntity saved =
                productRepository.save(product);

        productId = saved.getId();
    }

    @Test
    @DisplayName("재고 100개에 120개의 동시 차감 요청이 들어오면 100개만 성공한다")
    void decreaseInventoryConcurrency() throws Exception {

        int requestCount = 120;
        ExecutorService executorService = Executors.newFixedThreadPool(requestCount);
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch doneLatch = new CountDownLatch(requestCount);
        AtomicInteger successCount = new AtomicInteger();
        AtomicInteger failCount = new AtomicInteger();

        for (int i = 0; i < requestCount; i++) {
            executorService.submit(() -> {
                try {
                    startLatch.await();
                    productInventoryService.decreaseStocks(productId, 1);
                    successCount.incrementAndGet();
                } catch (Exception e) {
                    failCount.incrementAndGet();
                } finally {
                    doneLatch.countDown();
                }
            });

        }
        startLatch.countDown();
        boolean completed = doneLatch.await(10, TimeUnit.SECONDS);
        executorService.shutdown();
        assertThat(completed).isTrue();
        ProductEntity result = productRepository.findById(productId).orElseThrow();

        assertThat(result.getStocks()).isEqualTo(0);
        assertThat(successCount.get()).isEqualTo(100);
        assertThat(failCount.get()).isEqualTo(20);
    }
}