package com.example.ihealtzstore.web;

import com.example.ihealtzstore.model.entity.CartItemEntity;
import com.example.ihealtzstore.model.entity.ProductEntity;
import com.example.ihealtzstore.model.entity.ShoppingCartEntity;
import com.example.ihealtzstore.model.entity.UserEntity;
import com.example.ihealtzstore.service.CartItemService;
import com.example.ihealtzstore.service.ProductService;
import com.example.ihealtzstore.service.ShoppingCartService;
import com.example.ihealtzstore.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("shoppingCart")
public class ShoppingCartController {

    private final UserService userService;
    private final CartItemService cartItemService;
    private final ShoppingCartService shoppingCartService;
    private final ProductService productService;
    private final ModelMapper modelMapper;

    public ShoppingCartController(UserService userService, CartItemService cartItemService, ModelMapper modelMapper, ShoppingCartService shoppingCartService, ProductService productService) {
        this.userService = userService;
        this.cartItemService = cartItemService;
        this.modelMapper = modelMapper;
        this.shoppingCartService = shoppingCartService;
        this.productService = productService;
    }

    @GetMapping("/cart")
    public String shoppingCart(Model model, Principal principal) {

        UserEntity user = userService.findByUsername(principal.getName());

        ShoppingCartEntity shoppingCartEntity = user.getShoppingCart();

        List<CartItemEntity> cartItemEntityList = cartItemService.findByShoppingCart(shoppingCartEntity);

        shoppingCartService.updateShoppingCart(shoppingCartEntity);

        model.addAttribute("cartItemList", cartItemEntityList);
        model.addAttribute("shoppingCart", shoppingCartEntity);

        return "shoppingCart";
    }

    @PostMapping("/addItem")
    public String addItemConfirm(
            @ModelAttribute("product") ProductEntity product,
            @ModelAttribute("qty") String qty,
            Model model, Principal principal) {

        UserEntity user = userService.findByUsername(principal.getName());

        product = productService.findProductById(product.getId());

        if (Integer.parseInt(qty) > product.getInStockNumber()) {
            model.addAttribute("notEnoughStock", true);

            return "redirect:/products/productDetails?id=" + product.getId();
        }

        CartItemEntity cartItem = cartItemService.addProductToCartItem(product, user, Integer.parseInt(qty));
        model.addAttribute("addProductSuccess", true);

        return "redirect:/products/productDetails?id=" + product.getId();
    }
}
