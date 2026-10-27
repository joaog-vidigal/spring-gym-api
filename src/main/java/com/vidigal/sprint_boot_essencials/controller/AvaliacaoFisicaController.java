package com.vidigal.sprint_boot_essencials.controller;

import com.vidigal.sprint_boot_essencials.database.model.AvaliacoesFisicasEntity;
import com.vidigal.sprint_boot_essencials.dto.AvaliacoesFisicasDto;
import com.vidigal.sprint_boot_essencials.dto.AvaliacoesFisicasProjection;
import com.vidigal.sprint_boot_essencials.exception.BadRequestException;
import com.vidigal.sprint_boot_essencials.exception.NotFoundException;
import com.vidigal.sprint_boot_essencials.service.AvaliacaoFisicaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/avaliacoes")
@RequiredArgsConstructor
@Validated
public class AvaliacaoFisicaController {

    private final AvaliacaoFisicaService avaliacaoFisicaService;

    /*
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AvaliacoesFisicasEntity> findAll(){
        return avaliacaoFisicaService.findAll();
    }
    */

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createAvaliacao(@Valid @RequestBody AvaliacoesFisicasDto av) throws NotFoundException, BadRequestException {
        avaliacaoFisicaService.createAvaliacao(av);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AvaliacoesFisicasProjection> getAllAvaliacoes() {
        return avaliacaoFisicaService.getAllAvaliacoes();
    }

    @GetMapping("/page/{page}/size/{size}")
    @ResponseStatus(HttpStatus.OK)
    public Page<AvaliacoesFisicasProjection> getAllAvaliacoesPageable(@PathVariable Integer page, @PathVariable Integer size) {
        return avaliacaoFisicaService.getAllAvaliacoesPagable(page, size);
    }
}
