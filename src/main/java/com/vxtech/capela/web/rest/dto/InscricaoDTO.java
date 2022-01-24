package com.vxtech.capela.web.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InscricaoDTO {

    private Boolean isConfirmada;

    private PessoaDTO pessoa;

}
