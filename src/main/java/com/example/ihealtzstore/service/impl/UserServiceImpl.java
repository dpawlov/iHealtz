package com.example.ihealtzstore.service.impl;

import com.example.ihealtzstore.model.entity.RoleEntity;
import com.example.ihealtzstore.model.entity.UserEntity;
import com.example.ihealtzstore.model.enums.EnumRole;
import com.example.ihealtzstore.model.service.UserRegistrationServiceModel;
import com.example.ihealtzstore.repository.RoleRepository;
import com.example.ihealtzstore.repository.UserRepository;
import com.example.ihealtzstore.service.UserService;
import org.modelmapper.ModelMapper;
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

            UserEntity admin = new UserEntity();

            admin.setUsername("admin");
            admin.setFullName("Admin Adminov");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setEmail("admin@test.bg");
            admin.setRoles(List.of(adminRole, userRole));

            userRepository.save(admin);

            UserEntity user = new UserEntity();

            user.setUsername("ivan");
            user.setFullName("Ivan Cekov");
            user.setPassword(passwordEncoder.encode("12345"));
            user.setEmail("ivan@a.bg");
            user.setRoles(List.of(userRole));

            userRepository.save(user);

        }
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
