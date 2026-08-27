package com.example.product_service.adapter.in.web.response;

import com.example.product_service.domain.model.Product;

public record ProductResponse(
        Long id,
        String productName,
        int price,
        int stocks
) {

    public static ProductResponse from(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getProductName(),
                product.getPrice(),
                product.getProductStocks()
        );
    }
}