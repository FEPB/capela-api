package com.vxtech.capela.web.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OficinaDTO {

    private UUID id;

    @NotBlank
    private String descricao;

    @NotNull
    private LocalDate data;

    @NotNull
    @Min(0)
    private Integer vagas;

}
