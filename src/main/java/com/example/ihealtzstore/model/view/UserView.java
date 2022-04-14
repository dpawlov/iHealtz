package com.example.ihealtzstore.model.view;

import com.example.ihealtzstore.model.entity.OrderEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class UserView {

    private Long id;
    private String username;
    private String fullName;
    private List<OrderEntity> orders;

}
