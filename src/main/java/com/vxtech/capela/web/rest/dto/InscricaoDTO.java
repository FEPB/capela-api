package com.vxtech.capela.web.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.Valid;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InscricaoDTO {

    private UUID id;
    private Boolean isConfirmada;
    private Boolean isTrabalhador;
    private String telefone;
    private String instituicao;
    private String email;
    private String nomeCoordenador;
    private String emailCoordenador;
    private String restricaoSaude;
    private String restricaoAlimentar;

    @Valid
    private PessoaDTO pessoa;

    private OficinaDTO oficina;

}
