package com.example.ihealtzstore.model.entity;

import com.example.ihealtzstore.model.enums.EnumCountryStates;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_shipping_addresses")
public class UserShippingEntity extends BaseEntity {

    private String userReceiverName;
    private String userShippingStreet1;
    private String userShippingStreet2;
    private String userShippingCity;
    private String userShippingState;
    private String userShippingZipcode;
    private UserEntity user;
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @OneToOne
    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }
}
