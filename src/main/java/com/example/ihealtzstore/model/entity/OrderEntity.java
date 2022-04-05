package com.example.ihealtzstore.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity {

    private Date orderDate;
    private Date shippingDate;
    private String shippingMethod;
    private String orderStatus;
    private BigDecimal orderTotal;
    private List<CartItemEntity> cartItemList;

    @OneToMany(cascade = CascadeType.ALL)
    public List<CartItemEntity> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItemEntity> cartItemList) {
        this.cartItemList = cartItemList;
    }
}
