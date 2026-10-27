package com.vidigal.sprint_boot_essencials.service;

import com.vidigal.sprint_boot_essencials.database.model.AlunosEntity;
import com.vidigal.sprint_boot_essencials.database.model.AvaliacoesFisicasEntity;
import com.vidigal.sprint_boot_essencials.database.model.TreinosEntity;
import com.vidigal.sprint_boot_essencials.database.repository.IAlunosRepository;
import com.vidigal.sprint_boot_essencials.database.repository.IAvaliacoesFisicasRepository;
import com.vidigal.sprint_boot_essencials.database.repository.ITreinosRepository;
import com.vidigal.sprint_boot_essencials.dto.AlunosDto;
import com.vidigal.sprint_boot_essencials.exception.BadRequestException;
import com.vidigal.sprint_boot_essencials.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlunoService {
    private final IAlunosRepository alunosRepository;
    private final ITreinosRepository treinosRepository;
    private final IAvaliacoesFisicasRepository avaliacoesFisicasRepository;

    public List<AlunosEntity> findAll(){
        return alunosRepository.findAll();
    }

    public void createAluno(AlunosDto alunosDto) throws BadRequestException{
        AlunosEntity aluno = alunosRepository.findByEmail(alunosDto.getEmail()).orElse(null);

        if (aluno != null){
            throw new BadRequestException("Email já cadastrado");
        }

        alunosRepository.save(AlunosEntity.builder()
                .nome(alunosDto.getNome())
                .email(alunosDto.getEmail())
                .build()
        );
    }

    public AvaliacoesFisicasEntity getAlunoAvaliacao(Integer alunoId) throws NotFoundException{
        AlunosEntity aluno = alunosRepository.findById(alunoId).orElseThrow(() -> new NotFoundException("Aluno não encontrado"));

        AvaliacoesFisicasEntity avaliacao = aluno.getAvaliacaoFisica();
        if (avaliacao == null) {
            throw new NotFoundException("Avaliação não encontrada para este aluno");
        }

        return avaliacao;
    }

    @Transactional(rollbackFor = Exception.class) //Tratamento para qualquer tipo de exceção
    public void deleteAluno(Integer alunoId) throws NotFoundException{
        AlunosEntity aluno = alunosRepository.findById(alunoId).orElseThrow(() -> new NotFoundException("Aluno não encontrado"));

        //Deleta treinos
        List<Integer> treinosAlunoId = aluno.getTreinos().stream().map(TreinosEntity::getId).toList();

        treinosRepository.deleteAllById(treinosAlunoId);

        //Deleta aluno
        alunosRepository.delete(aluno);

        //Deleta avaliacao fisica
        avaliacoesFisicasRepository.deleteById(aluno.getAvaliacaoFisica().getId());

    }
}
