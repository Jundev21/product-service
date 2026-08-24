package com.example.product_service.application.port.in;

import com.example.product_service.domain.model.Product;

public interface ProductInfoUseCase {
    Product productDetails(Long productId);

    Product productLists();

    Product addNewProduct(
            String productName,
            int price,
            int productStocks
    );
}