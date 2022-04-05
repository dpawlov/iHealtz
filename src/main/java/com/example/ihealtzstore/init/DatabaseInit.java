package com.example.ihealtzstore.init;

import com.example.ihealtzstore.service.RoleService;
import com.example.ihealtzstore.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInit implements CommandLineRunner {

    private final RoleService roleService;
    private final UserService userService;

    public DatabaseInit(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        roleService.InitRoleInDb();
        userService.initializeUsers();
    }
}
