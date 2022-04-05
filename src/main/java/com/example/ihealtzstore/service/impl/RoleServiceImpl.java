package com.example.ihealtzstore.service.impl;

import com.example.ihealtzstore.model.entity.RoleEntity;
import com.example.ihealtzstore.model.enums.EnumRole;
import com.example.ihealtzstore.repository.RoleRepository;
import com.example.ihealtzstore.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void InitRoleInDb() {

        if(roleRepository.count() == 0) {

            Arrays.stream(EnumRole.values())
                    .forEach(currentRole -> {

                        RoleEntity roleEntity = new RoleEntity(currentRole);

                        roleRepository.save(roleEntity);
                    });
        }
    }

    @Override
    public RoleEntity getRole(Long id) {
        return roleRepository.findById(id).orElse(null);
    }
}
