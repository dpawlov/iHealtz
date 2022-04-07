package com.example.ihealtzstore.web;

import com.example.ihealtzstore.model.entity.CartItemEntity;
import com.example.ihealtzstore.model.entity.CreditCardEntity;
import com.example.ihealtzstore.model.entity.UserEntity;
import com.example.ihealtzstore.model.entity.UserShippingEntity;
import com.example.ihealtzstore.service.CartItemService;
import com.example.ihealtzstore.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class CheckoutController {

    private final UserService userService;
    private final CartItemService cartItemService;

    public CheckoutController(UserService userService, CartItemService cartItemService) {
        this.userService = userService;
        this.cartItemService = cartItemService;
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
}
