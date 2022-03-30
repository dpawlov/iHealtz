package com.example.ihealtzstore.web;

import com.example.ihealtzstore.model.binding.UserRegistrationBindingModel;
import com.example.ihealtzstore.model.service.UserRegistrationServiceModel;
import com.example.ihealtzstore.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {


    private final ModelMapper modelMapper;
    private final UserService userService;

    public UserController(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }


    // Model Attribute

    @ModelAttribute("registrationBindingModel")
    public UserRegistrationBindingModel createBindingModel() {
        return new UserRegistrationBindingModel();
    }

    // END


    // GetMapping

    @GetMapping("/register")
    public String create(Model model) {

        if (!model.containsAttribute("registrationBindingModel")) {
            model.addAttribute("registrationBindingModel", new UserRegistrationBindingModel());
        }

        if (!model.containsAttribute("registrationBindingModel")) {
            model.addAttribute("registrationBindingModel", new UserRegistrationBindingModel());
            model.addAttribute("userNameExist", false);
        }

        if (!model.containsAttribute("registrationBindingModel")) {
            model.addAttribute("registrationBindingModel", new UserRegistrationBindingModel());
            model.addAttribute("emailExist", false);
        }

        if (!model.containsAttribute("registrationBindingModel")) {
            model.addAttribute("registrationBindingModel", new UserRegistrationBindingModel());
            model.addAttribute("passwordIsNotEqual", false);
        }

        return "register";
    }


    @PostMapping("/register")
    public String createAccount(@Valid UserRegistrationBindingModel registrationBindingModel,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {


        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("registrationBindingModel", registrationBindingModel);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.registrationBindingModel", bindingResult);

            return "redirect:register";
        }


        if (userService.existByUsername(registrationBindingModel.getUsername())) {
            redirectAttributes.addFlashAttribute("registrationBindingModel", registrationBindingModel);
            redirectAttributes.addFlashAttribute("userNameExist", true);

            return "redirect:register";
        }


        if (userService.existByEmail(registrationBindingModel.getEmail())) {
            redirectAttributes.addFlashAttribute("registrationBindingModel", registrationBindingModel);
            redirectAttributes.addFlashAttribute("emailExist", true);
            return "redirect:register";
        }

        if (!registrationBindingModel.getPassword().equals(registrationBindingModel.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("registrationBindingModel", registrationBindingModel);
            redirectAttributes.addFlashAttribute("passwordIsNotEqual", true);
            return "redirect:register";
        }


        userService.register
                (modelMapper.map(registrationBindingModel, UserRegistrationServiceModel.class));


        return "redirect:login";


    }


    @PostMapping("/login-error")
    public String failedLogin(@ModelAttribute("username")
                                      String username,
                              RedirectAttributes attributes) {

        attributes.addFlashAttribute("username", username);
        attributes.addFlashAttribute("notLogged", true);
        return "redirect:login";

    }
}

