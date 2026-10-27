package com.vidigal.sprint_boot_essencials.service;

import com.vidigal.sprint_boot_essencials.database.model.ExerciciosEntity;
import com.vidigal.sprint_boot_essencials.database.repository.IExerciciosRepository;
import com.vidigal.sprint_boot_essencials.dto.ExerciciosDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciciosService {

    private final IExerciciosRepository exerciciosRepository;

    public List<ExerciciosEntity> findAll(){
        return exerciciosRepository.findAll();
    }

    public void save(ExerciciosDto ex){
        exerciciosRepository.save(ExerciciosEntity.builder()
                .nome(ex.getNome())
                .grupoMuscular(ex.getGrupoMuscular())
                .build()
        );
    }

    public List<ExerciciosEntity> getExerciciosByGrupoMuscular(String grupoMuscular){
        return exerciciosRepository.findAllByGrupoMuscular(grupoMuscular);
    }

}
