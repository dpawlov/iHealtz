package com.example.ihealtzstore.service.impl;

import com.example.ihealtzstore.model.entity.RoleEntity;
import com.example.ihealtzstore.model.entity.ShoppingCartEntity;
import com.example.ihealtzstore.model.entity.UserEntity;
import com.example.ihealtzstore.model.enums.EnumRole;
import com.example.ihealtzstore.model.service.UserPasswordUpdateServiceModel;
import com.example.ihealtzstore.model.service.UserRegistrationServiceModel;
import com.example.ihealtzstore.repository.RoleRepository;
import com.example.ihealtzstore.repository.UserRepository;
import com.example.ihealtzstore.service.UserService;
import com.example.ihealtzstore.web.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public void initializeUsers() {

        if (userRepository.count() == 0) {

            RoleEntity adminRole = roleRepository.findByRole(EnumRole.ADMIN).orElse(null);
            RoleEntity userRole = roleRepository.findByRole(EnumRole.USER).orElse(null);
            ShoppingCartEntity shoppingCartEntity = new ShoppingCartEntity();

            UserEntity admin = new UserEntity();

            admin.setUsername("admin");
            admin.setFullName("Admin Adminov");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setEmail("admin@test.bg");
            admin.setRoles(List.of(adminRole, userRole));
            admin.setShoppingCart(shoppingCartEntity);
            shoppingCartEntity.setUser(admin);

            userRepository.save(admin);

            UserEntity user = new UserEntity();

            user.setUsername("user");
            user.setFullName("User userov");
            user.setPassword(passwordEncoder.encode("user"));
            user.setEmail("user@user");
            user.setRoles(List.of(userRole));
            user.setShoppingCart(shoppingCartEntity);
            shoppingCartEntity.setUser(user);

            userRepository.save(user);

        }
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }


    @Override
    public UserEntity findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void updateUserPassword(UserPasswordUpdateServiceModel userPasswordUpdateServiceModel) {
        UserEntity userEntity = userRepository.findById(userPasswordUpdateServiceModel.getId()).orElseThrow(() ->
                new ObjectNotFoundException("User with id " + userPasswordUpdateServiceModel.getId() + " not found!"));


        userEntity.setPassword(passwordEncoder.encode(userPasswordUpdateServiceModel.getNewPassword()));

        userRepository.save(userEntity);
    }


    @Override
    public boolean existByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public void register(UserRegistrationServiceModel userRegistrationServiceModel) {

        // User init

        RoleEntity userRole = roleRepository.findByRole(EnumRole.USER).orElse(null);

        UserEntity userEntity = modelMapper.map(userRegistrationServiceModel, UserEntity.class);

        userEntity.setPassword(passwordEncoder.encode(userRegistrationServiceModel.getPassword()));

        userEntity.addRole(userRole);

        userRepository.save(userEntity);
    }
}
