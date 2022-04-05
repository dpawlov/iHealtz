package com.example.ihealtzstore.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "shopping_carts")
public class ShoppingCartEntity extends BaseEntity {

    private BigDecimal totalSum;
    private List<CartItemEntity> cartItemList;
    private UserEntity user;

    @OneToOne(cascade = CascadeType.ALL)
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @OneToMany(mappedBy = "shoppingCart")
    public List<CartItemEntity> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItemEntity> cartItemList) {
        this.cartItemList = cartItemList;
    }
}
