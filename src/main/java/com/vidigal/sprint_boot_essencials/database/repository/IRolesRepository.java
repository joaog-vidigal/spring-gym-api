package com.vidigal.sprint_boot_essencials.database.repository;

import com.vidigal.sprint_boot_essencials.database.model.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IRolesRepository extends JpaRepository<RolesEntity, Integer> {
    Optional<RolesEntity> findByNome(String nome);
}
