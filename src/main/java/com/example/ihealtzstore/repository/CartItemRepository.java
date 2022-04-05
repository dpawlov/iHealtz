package com.example.ihealtzstore.repository;

import com.example.ihealtzstore.model.entity.CartItemEntity;
import com.example.ihealtzstore.model.entity.ShoppingCartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {

    List<CartItemEntity> findByShoppingCart(ShoppingCartEntity shoppingCartEntity);
}
