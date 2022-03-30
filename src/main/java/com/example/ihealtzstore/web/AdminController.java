package com.example.ihealtzstore.web;

import com.example.ihealtzstore.model.binding.AddProductBindingModel;
import com.example.ihealtzstore.model.service.AddProductServiceModel;
import com.example.ihealtzstore.service.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ModelMapper modelMapper;
    private final AdminService adminService;

    public AdminController(ModelMapper modelMapper, AdminService adminService) {
        this.modelMapper = modelMapper;
        this.adminService = adminService;
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

        return "index";

    }

}
