package com.example.ihealtzstore.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity {

    private Date orderDate;
    private LocalDate shippingDate;
    private String shippingMethod;
    private String orderStatus;
    private BigDecimal orderTotal;
    private List<CartItemEntity> cartItemList;
    private UserShippingEntity shippingAddress;
    private CreditCardEntity payment;
    private UserEntity user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.MERGE)
    public List<CartItemEntity> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItemEntity> cartItemList) {
        this.cartItemList = cartItemList;
    }

    @OneToOne(cascade=CascadeType.ALL)
    public UserShippingEntity getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(UserShippingEntity shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    @OneToOne(cascade=CascadeType.ALL)
    public CreditCardEntity getPayment() {
        return payment;
    }

    public void setPayment(CreditCardEntity payment) {
        this.payment = payment;
    }

    @ManyToOne
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
