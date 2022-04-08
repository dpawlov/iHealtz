package com.example.ihealtzstore.service;

import com.example.ihealtzstore.model.entity.CartItemEntity;
import com.example.ihealtzstore.model.entity.ProductEntity;
import com.example.ihealtzstore.model.entity.ShoppingCartEntity;
import com.example.ihealtzstore.model.entity.UserEntity;

import java.util.List;

public interface CartItemService {
    List<CartItemEntity> findByShoppingCart(ShoppingCartEntity shoppingCartEntity);

    CartItemEntity updateCartItem(CartItemEntity cartItem);

    CartItemEntity addProductToCartItem(ProductEntity product, UserEntity user, int qty);

    CartItemEntity findById(Long cartItemId);

    void removeCartItem(CartItemEntity cartItem);

    void save(CartItemEntity cartItem);
}
