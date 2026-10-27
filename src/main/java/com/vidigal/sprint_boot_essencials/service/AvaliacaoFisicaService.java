package com.vidigal.sprint_boot_essencials.service;

import com.vidigal.sprint_boot_essencials.database.model.AlunosEntity;
import com.vidigal.sprint_boot_essencials.database.model.AvaliacoesFisicasEntity;
import com.vidigal.sprint_boot_essencials.database.repository.IAlunosRepository;
import com.vidigal.sprint_boot_essencials.database.repository.IAvaliacoesFisicasRepository;
import com.vidigal.sprint_boot_essencials.dto.AvaliacoesFisicasDto;
import com.vidigal.sprint_boot_essencials.dto.AvaliacoesFisicasProjection;
import com.vidigal.sprint_boot_essencials.exception.BadRequestException;
import com.vidigal.sprint_boot_essencials.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvaliacaoFisicaService {

    private final IAvaliacoesFisicasRepository avaliacoesFisicasRepository;
    private final IAlunosRepository alunosRepository;

    public List<AvaliacoesFisicasEntity> findAll(){
        return avaliacoesFisicasRepository.findAll();
    }

    public void createAvaliacao(AvaliacoesFisicasDto avaliacoesFisicasDto) throws NotFoundException, BadRequestException{
        AlunosEntity aluno = alunosRepository.findById(avaliacoesFisicasDto.getAlunoId()).orElseThrow(() -> new NotFoundException("Aluno não encontrado!"));

        AvaliacoesFisicasEntity avaliacaoFisica = aluno.getAvaliacaoFisica();

        if (avaliacaoFisica != null){
            throw new BadRequestException("Avaliação física já existente para esse aluno");
        }


        aluno.setAvaliacaoFisica(AvaliacoesFisicasEntity.builder()
                .peso(avaliacoesFisicasDto.getPeso())
                .altura(avaliacoesFisicasDto.getAltura())
                .porcentagemGorduraCorporal(avaliacoesFisicasDto.getPorcentagemGorduraCorporal())
                .build()
        );

        alunosRepository.save(aluno);
    }

    public List<AvaliacoesFisicasProjection> getAllAvaliacoes() {
        return avaliacoesFisicasRepository.getAllAvaliacoes();
    }

    public Page<AvaliacoesFisicasProjection> getAllAvaliacoesPagable(Integer page, Integer size) {
        return avaliacoesFisicasRepository.getAllAvaliacoesPage(PageRequest.of(page, size));
    }
}
