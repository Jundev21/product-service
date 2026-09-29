package com.example.product_service.application.port.in;

import com.example.product_service.domain.model.Product;

import java.util.List;

public interface ProductInfoUseCase {
    Product productDetails(Long productId);

    List<Product> productLists();

    Product addNewProduct(
            String productName,
            Long categoryId,
            int price,
            int productStocks
    );
}