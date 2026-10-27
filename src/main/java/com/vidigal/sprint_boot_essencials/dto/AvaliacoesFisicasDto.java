package com.vidigal.sprint_boot_essencials.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class AvaliacoesFisicasDto {

    @NotNull
    private Integer alunoId;

    @NotNull
    private BigDecimal peso;

    @NotNull
    private BigDecimal altura;

    @NotNull
    private BigDecimal porcentagemGorduraCorporal;
}
