package com.vidigal.sprint_boot_essencials.controller;

import com.vidigal.sprint_boot_essencials.database.model.AlunosEntity;
import com.vidigal.sprint_boot_essencials.database.model.AvaliacoesFisicasEntity;
import com.vidigal.sprint_boot_essencials.dto.AlunosDto;
import com.vidigal.sprint_boot_essencials.exception.BadRequestException;
import com.vidigal.sprint_boot_essencials.exception.NotFoundException;
import com.vidigal.sprint_boot_essencials.service.AlunoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/alunos")
@RequiredArgsConstructor
@Validated
public class AlunoController {
    private final AlunoService alunoService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AlunosEntity> findAll(){
        return alunoService.findAll();
    }

    @PreAuthorize("#alunoId == authentication.principal.id or hasRole('ADMIN')")
    @GetMapping("/{alunoId}/avaliacao")
    @ResponseStatus(HttpStatus.OK)
    public AvaliacoesFisicasEntity getAlunoAvaliacao(@PathVariable Integer alunoId) throws NotFoundException {
        return alunoService.getAlunoAvaliacao(alunoId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createAluno(@Valid @RequestBody AlunosDto alunosDto) throws BadRequestException {
        alunoService.createAluno(alunosDto);
    }

    @DeleteMapping("/{alunoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAluno(@PathVariable Integer alunoId) throws NotFoundException{
        alunoService.deleteAluno(alunoId);
    }
}
