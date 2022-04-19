package com.example.ihealtzstore.web;

import com.example.ihealtzstore.model.binding.UserPasswordUpdateBindingModel;
import com.example.ihealtzstore.model.binding.UserRegistrationBindingModel;
import com.example.ihealtzstore.model.entity.UserEntity;
import com.example.ihealtzstore.model.service.UserPasswordUpdateServiceModel;
import com.example.ihealtzstore.model.service.UserRegistrationServiceModel;
import com.example.ihealtzstore.service.UserService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/users")
public class UserController {

    private final ModelMapper modelMapper;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(ModelMapper modelMapper, UserService userService, PasswordEncoder passwordEncoder) {
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }


    // Model Attribute
    @ModelAttribute("registrationBindingModel")
    public UserRegistrationBindingModel createBindingModel() {
        return new UserRegistrationBindingModel();
    }


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


    @GetMapping("/profile")
    public String userProfile(Model model,
                              Principal principal) {

        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("user", userService.findByUsername(username));
        }

        return "userProfile";
    }


    @GetMapping("/editPassword/{id}")
    public String updateProfile(@PathVariable Long id,
                                Model model) {

        UserEntity user = userService.findById(id);

        UserPasswordUpdateBindingModel userPasswordUpdateBindingModel = modelMapper.map(
                user, UserPasswordUpdateBindingModel.class);

        if (!model.containsAttribute("userPasswordUpdateBindingModel")) {
            model.addAttribute("userPasswordUpdateBindingModel", new UserPasswordUpdateBindingModel());
            model.addAttribute("passwordIsNotEqual", false);
        }

        if (!model.containsAttribute("userPasswordUpdateBindingModel")) {
            model.addAttribute("userPasswordUpdateBindingModel", new UserPasswordUpdateBindingModel());
            model.addAttribute("currentPasswordNotEqual", false);
        }

        model.addAttribute("userPasswordUpdateBindingModel", userPasswordUpdateBindingModel);


        return "passwordUpdate";
    }

    @PostMapping("/editPassword/{id}")
    public String userProfileUpdateConfirm(@PathVariable Long id,
                                           @Valid UserPasswordUpdateBindingModel userPasswordUpdateBindingModel,
                                           BindingResult bindingResult,
                                           RedirectAttributes redirectAttributes) {

        UserEntity user = userService.findById(id);

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userPasswordUpdateBindingModel", userPasswordUpdateBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userPasswordUpdateBindingModel", bindingResult);

            return "redirect:/users/editPassword/" + id;
        }

        if (!userPasswordUpdateBindingModel.getNewPassword().equals(userPasswordUpdateBindingModel.getConfirmNewPassword())) {
            redirectAttributes.addFlashAttribute("userPasswordUpdateBindingModel", userPasswordUpdateBindingModel);
            redirectAttributes.addFlashAttribute("passwordIsNotEqual", true);
            return "redirect:/users/editPassword/" + id;
        }

        if (!passwordEncoder.matches(userPasswordUpdateBindingModel.getOldPassword(), user.getPassword())) {
            redirectAttributes.addFlashAttribute("userPasswordUpdateBindingModel", userPasswordUpdateBindingModel);
            redirectAttributes.addFlashAttribute("currentPasswordNotEqual", true);
            return "redirect:/users/editPassword/" + id;
        }


        userService.updateUserPassword(modelMapper.map(userPasswordUpdateBindingModel, UserPasswordUpdateServiceModel.class));

        return "passwordUpdate";
    }
}

