package com.vidigal.sprint_boot_essencials.database.repository;

import com.vidigal.sprint_boot_essencials.database.model.AlunosEntity;
import com.vidigal.sprint_boot_essencials.database.model.TreinosEntity;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ITreinosRepository extends JpaRepository<TreinosEntity, Integer> {

    Optional<TreinosEntity> findByNomeAndAlunoId(String nome, Integer alunoId);
}
