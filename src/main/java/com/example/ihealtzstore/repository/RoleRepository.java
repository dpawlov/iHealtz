package com.example.ihealtzstore.repository;

import com.example.ihealtzstore.model.entity.RoleEntity;
import com.example.ihealtzstore.model.enums.EnumRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByRole(EnumRole role);
}
