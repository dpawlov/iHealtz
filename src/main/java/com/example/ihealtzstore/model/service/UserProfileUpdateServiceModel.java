package com.example.ihealtzstore.model.service;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserProfileUpdateServiceModel {
    private Long id;

    private String username;
    private String fullName;
    private String email;
    private String password;
}
