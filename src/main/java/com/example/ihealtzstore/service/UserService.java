package com.example.ihealtzstore.service;

import com.example.ihealtzstore.model.service.UserRegistrationServiceModel;

public interface UserService {

    boolean existByUsername(String username);

    boolean existByEmail(String email);

    void register(UserRegistrationServiceModel userRegistrationServiceModel);

    void initializeUsers();
}
