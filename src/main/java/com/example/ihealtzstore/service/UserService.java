package com.example.ihealtzstore.service;

import com.example.ihealtzstore.model.entity.UserEntity;
import com.example.ihealtzstore.model.service.UserProfileUpdateServiceModel;
import com.example.ihealtzstore.model.service.UserRegistrationServiceModel;
import com.example.ihealtzstore.model.view.UserView;

public interface UserService {

    boolean existByUsername(String username);

    boolean existByEmail(String email);

    void register(UserRegistrationServiceModel userRegistrationServiceModel);

    void initializeUsers();

    UserEntity findByUsername(String username);

    void updateUserProfile(UserProfileUpdateServiceModel userProfileUpdateServiceModel);
}
