package com.example.ihealtzstore.service.impl;

import com.example.ihealtzstore.model.entity.*;
import com.example.ihealtzstore.repository.OrderRepository;
import com.example.ihealtzstore.service.CartItemService;
import com.example.ihealtzstore.service.OrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

        //ORDER
        OrderEntity order = new OrderEntity();
        order.setOrderStatus("created");
        order.setPayment(payment);
        order.setShippingAddress(shippingAddress);
        order.setShippingMethod(shippingMethod);
        order.setUser(user);


        List<CartItemEntity> cartItemList = cartItemService.findByShoppingCart(shoppingCart);

        for(CartItemEntity cartItem : cartItemList) {
            ProductEntity product = cartItem.getProduct();
            cartItem.setOrder(order);
            product.setInStockNumber(product.getInStockNumber() - cartItem.getQty());
        }

        order.setCartItemList(cartItemList);
        order.setOrderDate(Calendar.getInstance().getTime());
        order.setOrderTotal(shoppingCart.getTotalSum());

        LocalDate today = LocalDate.now();
        if (shippingMethod.equals("standard")) {
            order.setShippingDate(today.plusDays(5));
        } else {
            order.setShippingDate(today.plusDays(3));
        }

        //PAYMENT
        payment.setOrder(order);
        payment.setOwner(user);

        //SHIPPING ADDRESS
        shippingAddress.setOrder(order);
        shippingAddress.setUser(user);

        order = orderRepository.save(order);

        return order;
    }
}
