package com.vxtech.capela.web.rest.dto;

import com.vxtech.capela.enums.SexoEnum;
import com.vxtech.capela.enums.TipoSanguineoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaDTO {

    private UUID id;

    @NotBlank
    private String nome;
    private String nomeCracha;
    private TipoSanguineoEnum tipoSanguineo;

    @NotNull
    private LocalDateTime dataNascimento;

    @NotNull
    private SexoEnum genero;

}
