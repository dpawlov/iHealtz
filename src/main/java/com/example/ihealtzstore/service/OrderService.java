package com.example.ihealtzstore.service;

import com.example.ihealtzstore.model.entity.*;

public interface OrderService {
    OrderEntity createOrder(ShoppingCartEntity shoppingCart, UserShippingEntity shippingAddress, CreditCardEntity payment, String shippingMethod, UserEntity user);
}
