package com.example.ihealtzstore.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    private String username;
    private String fullName;
    private String password;
    private String email;
    private List<CreditCardEntity> card;
    private ShoppingCartEntity shoppingCart;
    private List<RoleEntity> roles = new ArrayList<>();
    private List<OrderEntity> orders;
    private List<UserShippingEntity> userShippingList;


    @OneToMany(mappedBy = "owner")
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<CreditCardEntity> getCard() {
        return card;
    }

    public void setCard(List<CreditCardEntity> card) {
        this.card = card;
    }

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    public ShoppingCartEntity getShoppingCart() {
        return shoppingCart;
    }
    public void setShoppingCart(ShoppingCartEntity shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }

    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderEntity> orders) {
        this.orders = orders;
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user")
    public List<UserShippingEntity> getUserShippingList() {
        return userShippingList;
    }

    public void setUserShippingList(List<UserShippingEntity> userShippingList) {
        this.userShippingList = userShippingList;
    }

    public UserEntity addRole(RoleEntity roleEntity) {
        this.roles.add(roleEntity);
        return this;
    }

    public UserEntity removeRole(RoleEntity roleEntity) {
        this.roles.remove(roleEntity);
        return this;
    }
}

