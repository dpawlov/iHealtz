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

    private String userShippingName;
    private String userShippingStreet1;
    private String userShippingStreet2;
    private String userShippingCity;
    private String userShippingState;
    private String userShippingZipcode;
    private String userShippingCountry;
    private EnumCountryStates countryStates;
    private UserEntity user;

    @Enumerated(EnumType.STRING)
    public EnumCountryStates getCountryStates() {
        return countryStates;
    }

    public void setCountryStates(EnumCountryStates countryStates) {
        this.countryStates = countryStates;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
