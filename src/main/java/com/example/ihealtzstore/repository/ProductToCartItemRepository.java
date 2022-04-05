package com.example.ihealtzstore.repository;

import com.example.ihealtzstore.model.entity.ProductToCartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductToCartItemRepository extends JpaRepository<ProductToCartItemEntity, Long> {
}