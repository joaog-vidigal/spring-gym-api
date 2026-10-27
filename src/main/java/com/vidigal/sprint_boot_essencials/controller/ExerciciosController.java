package com.vidigal.sprint_boot_essencials.controller;

import com.vidigal.sprint_boot_essencials.database.model.ExerciciosEntity;
import com.vidigal.sprint_boot_essencials.dto.ExerciciosDto;
import com.vidigal.sprint_boot_essencials.service.ExerciciosService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/exercicios")
@RequiredArgsConstructor
@Validated
public class ExerciciosController {

    private final ExerciciosService exerciciosService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ExerciciosEntity> findAll(){
        return exerciciosService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@Valid @RequestBody ExerciciosDto ex){
        exerciciosService.save(ex);
    }

    @GetMapping("/grupos/{grupoMuscular}")
    @ResponseStatus(HttpStatus.OK)
    public List<ExerciciosEntity> getExerciciosByGrupoMuscular(@PathVariable String grupoMuscular){
        return exerciciosService.getExerciciosByGrupoMuscular(grupoMuscular);
    }
}
