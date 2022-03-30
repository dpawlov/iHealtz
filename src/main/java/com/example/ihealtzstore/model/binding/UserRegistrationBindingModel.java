package com.example.ihealtzstore.model.binding;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRegistrationBindingModel {

    private String username;
    private String fullName;
    private String email;
    private String password;
    private String confirmPassword;
}
