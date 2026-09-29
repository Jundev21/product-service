package com.example.product_service.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name = "products",
        indexes = {
                @Index(
                        name = "idx_products_category_price",
                        columnList = "category_id, price"
                )
        }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;

    private Long categoryId;

    private int price;

    private int stocks;

    public ProductEntity(
            Long id,
            String productName,
            Long categoryId,
            int price,
            int stocks
    ) {
        this.id = id;
        this.productName = productName;
        this.categoryId = categoryId;
        this.price = price;
        this.stocks = stocks;

    }
}
