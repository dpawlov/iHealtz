package com.example.ihealtzstore.service;

import com.example.ihealtzstore.model.entity.RoleEntity;

public interface RoleService {
    void InitRoleInDb();
    RoleEntity getRole(Long id);
}
