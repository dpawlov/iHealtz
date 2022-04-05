package com.example.ihealtzstore.model.view;

import com.example.ihealtzstore.model.entity.CartItemEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ShoppingCartView {

    private BigDecimal totalSum;
    private List<CartItemEntity> cartItemList;
}
