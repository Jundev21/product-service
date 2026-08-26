package com.example.product_service.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Getter
@NoArgsConstructor
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;

    private int price;

    private int stocks;

    public ProductEntity(
            Long id,
            String productName,
            int price,
            int stocks
    ) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.stocks = stocks;

    }
}
