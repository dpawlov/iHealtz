package com.example.ihealtzstore.repository;

import com.example.ihealtzstore.model.entity.CartItemEntity;
import com.example.ihealtzstore.model.entity.ProductToCartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ProductToCartItemRepository extends JpaRepository<ProductToCartItemEntity, Long> {
    void deleteByCartItem(CartItemEntity cartItem);
}
