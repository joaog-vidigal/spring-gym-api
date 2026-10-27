package com.vidigal.sprint_boot_essencials.controller;

import com.vidigal.sprint_boot_essencials.database.model.TreinosEntity;
import com.vidigal.sprint_boot_essencials.dto.TreinoDto;
import com.vidigal.sprint_boot_essencials.exception.BadRequestException;
import com.vidigal.sprint_boot_essencials.exception.NotFoundException;
import com.vidigal.sprint_boot_essencials.service.TreinoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/v1/treinos")
@RequiredArgsConstructor
@Validated
public class TreinosController {

    private final TreinoService treinoService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TreinosEntity> findAll() {
        return treinoService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTreino(@Valid @RequestBody TreinoDto treinoDto) throws NotFoundException, BadRequestException {
        treinoService.createTreino(treinoDto);
    }
}
