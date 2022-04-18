package com.example.ihealtzstore.model.service;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserPasswordUpdateServiceModel {
    private Long id;
    private String oldPassword;
    private String newPassword;
}
