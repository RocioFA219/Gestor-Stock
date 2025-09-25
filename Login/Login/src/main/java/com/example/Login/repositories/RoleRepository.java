package com.example.Login.repositories;

import com.example.Login.enums.EnumRole;
import com.example.Login.model.RoleEntity;
import jakarta.persistence.Enumerated;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByRole(EnumRole role);
}
