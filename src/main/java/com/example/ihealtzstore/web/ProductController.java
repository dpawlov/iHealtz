package com.example.ihealtzstore.web;

import com.example.ihealtzstore.model.entity.UserEntity;
import com.example.ihealtzstore.service.ProductService;
import com.example.ihealtzstore.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.websocket.server.PathParam;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final UserService userService;

    public ProductController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("/allProducts")
    public String allProducts(Model model,
                              Principal principal) {

        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("user", userService.findByUsername(username));
        }

        model.addAttribute("allProducts", productService.findAll());

        return "allProducts";
    }

    @GetMapping("/productDetails")
    public String productDetails(@PathParam("id") Long id,
                                 Model model,
                                 Principal principal) {

        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("user", userService.findByUsername(username));
        }

        model.addAttribute("product", productService.findById(id));

        List<Integer> qtyList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        model.addAttribute("qtyList", qtyList);
        model.addAttribute("qty", 1);

        return "productDetails";
    }
}
