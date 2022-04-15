package com.example.ihealtzstore.model.service;
import com.example.ihealtzstore.model.entity.OrderEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserProfileUpdateServiceModel {
    private Long id;

    private String username;
    private String fullName;
    private String email;
    private String password;
    private List<OrderEntity> orders;
}
