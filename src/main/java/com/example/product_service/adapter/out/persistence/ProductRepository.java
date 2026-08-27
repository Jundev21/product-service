package com.example.product_service.adapter.out.persistence;

import com.example.product_service.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Modifying(
            // update나 delete 는 영속성컨텍스트에 반영하지않고 바로 DB 쿼리에 나가기때문에 영속성 컨텍스트 데이터랑 일치시키기위해서 clear사용
            clearAutomatically = true,
            // 쿼리 실행하기전에 디비에 반영되지않은 영속성 컨텍스트 데이터들을 미리 반영하고 후에 처리한다
            flushAutomatically = true
    )

    @Query("""
            Update ProductEntity p\s
            SET p.stocks = p.stocks-:quantity
            WHERE p.id =:productId AND p.stocks>= :quantity
           \s""")
    int decreaseInventory(@Param("productId") Long productId, @Param("quantity") int quantity);

}
