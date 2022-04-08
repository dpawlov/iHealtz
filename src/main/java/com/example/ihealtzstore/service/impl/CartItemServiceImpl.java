package com.example.ihealtzstore.service.impl;

import com.example.ihealtzstore.model.entity.*;
import com.example.ihealtzstore.repository.CartItemRepository;
import com.example.ihealtzstore.repository.ProductToCartItemRepository;
import com.example.ihealtzstore.service.CartItemService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;
    private final ProductToCartItemRepository productToCartItemRepository;
    private final ModelMapper modelMapper;

    public CartItemServiceImpl(CartItemRepository cartItemRepository, ProductToCartItemRepository productToCartItemRepository, ModelMapper modelMapper) {
        this.cartItemRepository = cartItemRepository;
        this.productToCartItemRepository = productToCartItemRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CartItemEntity> findByShoppingCart(ShoppingCartEntity shoppingCartEntity) {
        return cartItemRepository.findByShoppingCart(shoppingCartEntity);
    }

    @Override
    public CartItemEntity updateCartItem(CartItemEntity cartItem) {
        BigDecimal bigDecimal = BigDecimal.valueOf(cartItem.getProduct().getDiscountPrice()).multiply(new BigDecimal(cartItem.getQty()));

        bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
        cartItem.setProductPrice(bigDecimal);

        cartItemRepository.save(cartItem);

        return cartItem;
    }

    @Override
    public CartItemEntity addProductToCartItem(ProductEntity product, UserEntity user, int qty) {

        List<CartItemEntity> cartItemList = findByShoppingCart(user.getShoppingCart());

        for (CartItemEntity cartItem : cartItemList) {
            if(product.getId().equals(cartItem.getProduct().getId())) {
                cartItem.setQty(cartItem.getQty());
                cartItem.setProductPrice(BigDecimal.valueOf(product.getDiscountPrice()).multiply(new BigDecimal(qty)));
                cartItemRepository.save(cartItem);
                return cartItem;
            }
        }
        CartItemEntity cartItem = new CartItemEntity();
        cartItem.setShoppingCart(user.getShoppingCart());
        cartItem.setProduct(product);

        cartItem.setQty(qty);
        cartItem.setProductPrice(BigDecimal.valueOf(product.getDiscountPrice()).multiply(new BigDecimal(qty)));
        cartItem = cartItemRepository.save(cartItem);

        ProductToCartItemEntity productToCartItem = new ProductToCartItemEntity();
        productToCartItem.setProduct(product);
        productToCartItem.setCartItem(cartItem);
        productToCartItemRepository.save(productToCartItem);

        return cartItem;
    }

    @Override
    public CartItemEntity findById(Long cartItemId) {
        return cartItemRepository.findById(cartItemId).orElse(null);
    }

    @Override
    public void removeCartItem(CartItemEntity cartItem) {
        productToCartItemRepository.deleteByCartItem(cartItem);
        cartItemRepository.delete(cartItem);
    }

    @Override
    public void save(CartItemEntity cartItem) {
        cartItemRepository.save(cartItem);
    }
}
