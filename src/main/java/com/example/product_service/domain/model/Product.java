package com.example.product_service.domain.model;


public record Product(Long id, String productName, Long categoryId, int price, int productStocks) {
    public static Product create(
            Long id, String name, int price, int productStocks, Long categoryId
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
                id, name, categoryId, price, productStocks
        );
    }
}
