package com.example.ihealtzstore.web;

import com.example.ihealtzstore.model.binding.AddProductBindingModel;
import com.example.ihealtzstore.model.binding.UpdateProductBindingModel;
import com.example.ihealtzstore.model.entity.ProductEntity;
import com.example.ihealtzstore.model.service.AddProductServiceModel;
import com.example.ihealtzstore.model.service.UpdateProductServiceModel;
import com.example.ihealtzstore.model.view.ProductsView;
import com.example.ihealtzstore.service.AdminService;
import com.example.ihealtzstore.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ModelMapper modelMapper;
    private final AdminService adminService;
    private final ProductService productService;

    public AdminController(ModelMapper modelMapper, AdminService adminService, ProductService productService) {
        this.modelMapper = modelMapper;
        this.adminService = adminService;
        this.productService = productService;
    }

    @GetMapping("/adminPortal")
    public String admin() {

        return "adminPortal";
    }

    @GetMapping("/addProduct")
    public String addProduct(Model model) {

        if (!model.containsAttribute("addProductBindingModel")) {
            model.addAttribute("addProductBindingModel", new AddProductBindingModel());
        }

        return "addProduct";
    }

    @PostMapping("/addProduct")
    public String addProductConfirm(@Valid AddProductBindingModel addProductBindingModel,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addProductBindingModel", addProductBindingModel);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.addProductBindingModel", bindingResult);

            return "redirect:addProduct";
        }

        adminService.addProduct(modelMapper.map(addProductBindingModel, AddProductServiceModel.class));

        return "";

    }

    @GetMapping("/adminProductList")
    public String adminProductListManageView(Model model) {

        model.addAttribute("adminProductList", productService.getAll());

        return "adminProductList";
    }

    @GetMapping("/updateProduct/{id}")
    public String updateProduct(@PathVariable Long id,
                                Model model) {

        ProductEntity updateProduct = productService.findProductById(id);

        UpdateProductBindingModel updateProductBindingModel = modelMapper.map(
            updateProduct, UpdateProductBindingModel.class);

        model.addAttribute("updateProductBindingModel", updateProductBindingModel);

        return "/adminUpdateProduct";
    }

    @PostMapping("/updateProduct/{id}")
    public String updateProductConfirm(@PathVariable Long id,
                                       @Valid UpdateProductBindingModel updateProductBindingModel,
                                       BindingResult bindingResult,
                                       RedirectAttributes redirectAttributes) {


        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("updateProductBindingModel", updateProductBindingModel);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.updateProductBindingModel", bindingResult);

            return "redirect:/admin/updateProduct/" + id;
        }

        adminService.updateProduct(modelMapper.map(updateProductBindingModel, UpdateProductServiceModel.class));

        return "redirect:/admin/adminProductList";
    }

    @PostMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable Long id) {

        productService.deleteProductById(id);

        return "redirect:/admin/adminProductList";
    }
}
