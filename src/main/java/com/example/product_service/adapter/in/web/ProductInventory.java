package com.example.product_service.adapter.in.web;

import com.example.product_service.adapter.in.web.response.DecreaseInventoryRequest;
import com.example.product_service.application.port.in.ProductInventoryUseCase;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductInventory {

    private final ProductInventoryUseCase productInventoryUseCase;

    @PostMapping("/{productId}/inventory/decrease")
    public ResponseEntity<Void> decreaseInventory(
            @PathVariable Long productId,
            @Valid @RequestBody DecreaseInventoryRequest request
    ) {

        productInventoryUseCase.decreaseStocks(
                productId,
                request.quantity()
        );

        return ResponseEntity.noContent().build();
    }


}