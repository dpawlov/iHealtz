package com.example.ihealtzstore.service.impl;

import com.example.ihealtzstore.model.entity.CartItemEntity;
import com.example.ihealtzstore.model.entity.ShoppingCartEntity;
import com.example.ihealtzstore.repository.ShoppingCartRepository;
import com.example.ihealtzstore.service.CartItemService;
import com.example.ihealtzstore.service.ShoppingCartService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final CartItemService cartItemService;
    private final ShoppingCartRepository shoppingCartRepository;
    private final ModelMapper modelMapper;

    public ShoppingCartServiceImpl(CartItemService cartItemService, ShoppingCartRepository shoppingCartRepository, ModelMapper modelMapper) {
        this.cartItemService = cartItemService;
        this.shoppingCartRepository = shoppingCartRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void updateShoppingCart(ShoppingCartEntity shoppingCartEntity) {
        BigDecimal cartTotalSum = new BigDecimal(0);

        List<CartItemEntity> cartItemList = cartItemService.findByShoppingCart(shoppingCartEntity);


        for (CartItemEntity cartItem : cartItemList) {
            if(cartItem.getProduct().getInStockNumber() > 0) {
                cartItemService.updateCartItem(cartItem);
                cartTotalSum = cartTotalSum.add(cartItem.getProductPrice());
            }
        }
        shoppingCartEntity.setTotalSum(cartTotalSum);

        shoppingCartRepository.save(shoppingCartEntity);

    }
}
