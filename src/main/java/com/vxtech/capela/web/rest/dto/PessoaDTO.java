package com.vxtech.capela.web.rest.dto;

import com.vxtech.capela.enums.SexoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaDTO {

    private String nome;
    private LocalDateTime dataNascimento;
    private SexoEnum genero;

}
