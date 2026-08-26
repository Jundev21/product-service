package com.example.product_service.domain.model;


import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class Product {
    private final Long id;
    private final String productName;
    private final int price;
    private final int productStocks;

    public Product(String productName, int price, Long id, int productStocks) {
        this.productName = productName;
        this.price = price;
        this.id = id;
        this.productStocks = productStocks;
    }

    public static Product create(
            String name, int price, int productStocks
    ) {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("상품명은 필수입니다.");
        }

        if (price <= 0) {
            throw new IllegalArgumentException("상품 가격은 0보다 커야 합니다.");
        }

        if (productStocks < 0) {
            throw new IllegalArgumentException("재고는 0보다 작을 수 없습니다.");
        }

        return new Product(
                name, price, null, productStocks
        );
    }
}
