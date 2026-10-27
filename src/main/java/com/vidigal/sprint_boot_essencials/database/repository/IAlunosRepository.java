package com.vidigal.sprint_boot_essencials.database.repository;

import com.vidigal.sprint_boot_essencials.database.model.AlunosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface IAlunosRepository extends JpaRepository<AlunosEntity, Integer> {

    Optional<AlunosEntity> findByEmail(String email);
}
