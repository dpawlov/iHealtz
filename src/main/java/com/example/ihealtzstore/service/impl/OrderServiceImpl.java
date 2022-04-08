package com.example.ihealtzstore.service.impl;

import com.example.ihealtzstore.model.entity.*;
import com.example.ihealtzstore.repository.OrderRepository;
import com.example.ihealtzstore.service.CartItemService;
import com.example.ihealtzstore.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final CartItemService cartItemService;
    private final OrderRepository orderRepository;

    public OrderServiceImpl(CartItemService cartItemService, OrderRepository orderRepository) {
        this.cartItemService = cartItemService;
        this.orderRepository = orderRepository;
    }


    @Override
    public OrderEntity createOrder(ShoppingCartEntity shoppingCart, UserShippingEntity shippingAddress, CreditCardEntity payment, String shippingMethod, UserEntity user) {

        OrderEntity order = new OrderEntity();
        order.setOrderStatus("created");
        order.setPayment(payment);
        order.setShippingAddress(shippingAddress);
        order.setShippingMethod(shippingMethod);

        List<CartItemEntity> cartItemList = cartItemService.findByShoppingCart(shoppingCart);

        for(CartItemEntity cartItem : cartItemList) {
            ProductEntity product = cartItem.getProduct();
            cartItem.setOrder(order);
            product.setInStockNumber(product.getInStockNumber() - cartItem.getQty());
        }

        order.setCartItemList(cartItemList);
        order.setOrderDate(Calendar.getInstance().getTime());
        order.setOrderTotal(shoppingCart.getTotalSum());
        shippingAddress.setOrder(order);
        payment.setOrder(order);
        order.setUser(user);
        order = orderRepository.save(order);

        return order;
    }
}
