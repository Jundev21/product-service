package com.example.product_service.application.port.out;

import com.example.product_service.domain.model.Product;

import java.util.List;

public interface ProductInfoPort {
    Product save(Product product);

    Product searchProductDetail(Long productId);

    List<Product> searchProductList();
}
