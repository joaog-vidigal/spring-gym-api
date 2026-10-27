package com.vidigal.sprint_boot_essencials.service;

import com.vidigal.sprint_boot_essencials.database.model.AlunosEntity;
import com.vidigal.sprint_boot_essencials.database.model.ExerciciosEntity;
import com.vidigal.sprint_boot_essencials.database.model.TreinosEntity;
import com.vidigal.sprint_boot_essencials.database.repository.IAlunosRepository;
import com.vidigal.sprint_boot_essencials.database.repository.IExerciciosRepository;
import com.vidigal.sprint_boot_essencials.database.repository.ITreinosRepository;
import com.vidigal.sprint_boot_essencials.dto.TreinoDto;
import com.vidigal.sprint_boot_essencials.exception.BadRequestException;
import com.vidigal.sprint_boot_essencials.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TreinoService {

    private final ITreinosRepository treinosRepository;
    private final IExerciciosRepository exerciciosRepository;
    private final IAlunosRepository alunosRepository;

    public List<TreinosEntity> findAll(){
        return treinosRepository.findAll();
    }

    public void createTreino(TreinoDto treinoDto) throws NotFoundException, BadRequestException {
        Set<ExerciciosEntity> exercicios = new HashSet<>();

        AlunosEntity aluno = alunosRepository.findById(treinoDto.getAlunoId()).orElseThrow(() -> new NotFoundException("Aluno não encontrado"));

        TreinosEntity treino = treinosRepository.findByNomeAndAlunoId(treinoDto.getNome(), treinoDto.getAlunoId()).orElse(null);

        if (treino != null) {
            throw new BadRequestException("Treino já existente para este aluno");
        }

        for (Integer exercicioId : treinoDto.getExerciciosIds()) {
            ExerciciosEntity exercicio = exerciciosRepository.findById(exercicioId).orElseThrow(() -> new NotFoundException("Exercício não encontrado"));

            exercicios.add(exercicio);
        }

        treino = TreinosEntity.builder()
                .nome(treinoDto.getNome())
                .aluno(aluno)
                .exercicios(exercicios)
                .build();

        treinosRepository.save(treino);

    }
}
