package com.example.ihealtzstore.model.entity;

import com.example.ihealtzstore.model.enums.EnumRole;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class RoleEntity extends BaseEntity{

    private EnumRole role;


    public RoleEntity() {
    }

    public RoleEntity(EnumRole role) {
        this.role = role;
    }

    @Enumerated(EnumType.STRING)
    public EnumRole getRole() {
        return role;
    }

    public void setRole(EnumRole role) {
        this.role = role;
    }


}
