package com.example.product_service.application.service;


import com.example.product_service.application.port.in.ProductInfoUseCase;
import com.example.product_service.domain.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductInfoService implements ProductInfoUseCase {

    @Override
    public Product productDetails(Long productId) {
        return null;
    }

    @Override
    public Product productLists() {
        return null;
    }

    @Override
    public Product addNewProduct(String productName, int price, int productStocks) {
        return null;
    }
}
