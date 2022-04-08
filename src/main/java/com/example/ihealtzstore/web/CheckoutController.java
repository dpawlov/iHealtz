package com.example.ihealtzstore.web;

import com.example.ihealtzstore.model.entity.*;
import com.example.ihealtzstore.repository.OrderRepository;
import com.example.ihealtzstore.service.CartItemService;
import com.example.ihealtzstore.service.OrderService;
import com.example.ihealtzstore.service.ShoppingCartService;
import com.example.ihealtzstore.service.UserService;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
public class CheckoutController {

    private final UserService userService;
    private final CartItemService cartItemService;
    private final OrderService orderService;
    private final ShoppingCartService shoppingCartService;
    private final OrderRepository orderRepository;

    public CheckoutController(UserService userService, CartItemService cartItemService, OrderService orderService, ShoppingCartService shoppingCartService, OrderRepository orderRepository) {
        this.userService = userService;
        this.cartItemService = cartItemService;
        this.orderService = orderService;
        this.shoppingCartService = shoppingCartService;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/checkout")
    public String checkout(@RequestParam("id") Long cartId,
                           @RequestParam(value = "missingRequiredField", required = false) boolean missingRequiredField,
                           Model model,
                           Principal principal) {

        UserEntity user = userService.findByUsername(principal.getName());

        if (!cartId.equals(user.getShoppingCart().getId())) {
            return "redirect:/badRequestPage";
        }

        List<CartItemEntity> cartItemList = cartItemService.findByShoppingCart(user.getShoppingCart());

        if (cartItemList.size() == 0) {
            model.addAttribute("emptyCart", true);
            return "redirect:/shoppingCart/cart";
        }

        for (CartItemEntity cartItem : cartItemList) {
            if (cartItem.getProduct().getInStockNumber() < cartItem.getQty()) {
                model.addAttribute("notEnoughStock", true);
                return "redirect:/shoppingCart/cart";
            }
        }

        model.addAttribute("shippingAddress", new UserShippingEntity());
        model.addAttribute("payment", new CreditCardEntity());
        model.addAttribute("shoppingCart", user.getShoppingCart());
        model.addAttribute("cartItemList", cartItemList);

        if (missingRequiredField) {
            model.addAttribute("missingRequiredField", true);
        }

        return "checkout";

    }

    @PostMapping("/checkout")
    public String checkoutConfirm(@ModelAttribute("shippingAddress") UserShippingEntity shippingAddress,
                                  @ModelAttribute("payment") CreditCardEntity payment,
                                  @ModelAttribute("shippingMethod") String shippingMethod,
                                  Principal principal, Model model) {
        ShoppingCartEntity shoppingCart = userService.findByUsername(principal.getName()).getShoppingCart();

        List<CartItemEntity> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
        model.addAttribute("cartItemList", cartItemList);

        UserEntity user = userService.findByUsername(principal.getName());

        OrderEntity order = orderService.createOrder(shoppingCart, shippingAddress, payment, shippingMethod, user);

        shoppingCartService.clearShoppingCart(shoppingCart);

        LocalDate today = LocalDate.now();
        LocalDate estimatedDeliveryDate;

        if (shippingMethod.equals("standard")) {
            estimatedDeliveryDate = today.plusDays(5);
        } else {
            estimatedDeliveryDate = today.plusDays(3);
        }

        model.addAttribute("estimatedDeliveryDate", estimatedDeliveryDate);

        orderRepository.save(order);

        return "orderSubmittedPage";
    }
}
