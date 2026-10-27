package com.vidigal.sprint_boot_essencials.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class AlunosDto {

    @NotBlank
    private String nome;

    @NotBlank
    private String email;
}
