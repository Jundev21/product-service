package com.example.product_service.application.service;


import com.example.product_service.application.port.in.ProductInfoUseCase;
import com.example.product_service.application.port.out.ProductInfoPort;
import com.example.product_service.domain.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductInfoService implements ProductInfoUseCase {

    private final ProductInfoPort productInfoPort;

    @Override
    public Product productDetails(Long productId) {
        return productInfoPort.searchProductDetail(productId);
    }

    @Override
    public List<Product> productLists() {
        return productInfoPort.searchProductList();
    }

    @Override
    public Product addNewProduct(String productName, int price, int productStocks) {
        Product newProduct = new Product(productName, price, null, productStocks);
        return productInfoPort.save(newProduct);
    }
}
