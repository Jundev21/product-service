package com.example.product_service.adapter.in.web;

import com.example.product_service.adapter.in.web.request.CreateProductRequest;
import com.example.product_service.adapter.in.web.response.ProductResponse;
import com.example.product_service.application.port.in.ProductInfoUseCase;
import com.example.product_service.domain.model.Product;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductInfo {

    private final ProductInfoUseCase productInfoUseCase;

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(
            @Valid @RequestBody CreateProductRequest request
    ) {

        Product product = productInfoUseCase.addNewProduct(
                request.productName(),
                request.price(),
                request.stocks()
        );

        return ResponseEntity.ok(
                ProductResponse.from(product)
        );
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProduct(
            @PathVariable Long productId
    ) {

        Product product = productInfoUseCase.productDetails(productId);

        return ResponseEntity.ok(
                ProductResponse.from(product)
        );
    }

}