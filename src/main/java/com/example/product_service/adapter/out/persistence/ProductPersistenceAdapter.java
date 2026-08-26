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
                        product.getProductName(),
                        product.getPrice(),
                        product.getProductStocks()
                );

        ProductEntity savedProductEntity = productRepository.save(productEntity);

        return new Product(
                savedProductEntity.getProductName(),
                savedProductEntity.getPrice(),
                savedProductEntity.getId(),
                savedProductEntity.getStocks()
        );
    }

    @Override
    public Product searchProductDetail(Long productId) {
        ProductEntity productEntity = productRepository.findById(productId).orElseThrow();
        return new Product(productEntity.getProductName(), productEntity.getPrice(), productEntity.getId(), productEntity.getStocks());
    }

    @Override
    public List<Product> searchProductList() {
        List<ProductEntity> productEntity = productRepository.findAll();

        return productEntity.stream().map(product ->
                new Product(product.getProductName(), product.getPrice(), product.getId(), product.getStocks())
        ).toList();

    }
}
