package com.example.product_service.adapter.out.persistence;

import com.example.product_service.application.port.out.ProductInfoPort;
import com.example.product_service.domain.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ProductPersistenceAdapter implements ProductInfoPort {

    private final ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        ProductEntity productEntity =
                new ProductEntity(
                        null,
                        product.productName(),
                        product.categoryId(),
                        product.price(),
                        product.productStocks()

                );

        ProductEntity savedProductEntity = productRepository.save(productEntity);

        return new Product(
                savedProductEntity.getId(),
                savedProductEntity.getProductName(),
                savedProductEntity.getCategoryId(),
                savedProductEntity.getPrice(),
                savedProductEntity.getStocks()
        );
    }

    @Override
    public Product searchProductDetail(Long productId) {

        System.out.println("productId: " + productId);
        ProductEntity productEntity = productRepository.findById(productId).orElseThrow(
                () -> new IllegalArgumentException("상품없음")
        );
        return new Product(
                productEntity.getId(),
                productEntity.getProductName(),
                productEntity.getCategoryId(),
                productEntity.getPrice(),
                productEntity.getStocks()
        );
    }

    @Override
    public List<Product> searchProductList() {
        List<ProductEntity> productEntity = productRepository.findAll();

        return productEntity.stream().map(product ->
                new Product(
                        product.getId(),
                        product.getProductName(),
                        product.getCategoryId(),
                        product.getPrice(),
                        product.getStocks()
                )
        ).toList();

    }
}
