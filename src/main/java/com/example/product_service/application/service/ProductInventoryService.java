package com.example.product_service.application.service;


import com.example.product_service.application.port.out.ProductInfoPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductInventoryService {
    private final ProductInfoPort productInfoPort;


}
