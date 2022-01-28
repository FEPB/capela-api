package com.vxtech.capela.model;

import com.vxtech.capela.enums.SexoEnum;
import com.vxtech.capela.enums.TipoSanguineoEnum;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "pessoas")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Builder(toBuilder=true)
@DynamicInsert
@DynamicUpdate
public class Pessoa extends AbstractModel {

    private String nome;

    @Column(name = "nome_cracha")
    private String nomeCracha;

    @Enumerated
    @Column(name = "tipo_sanguineo")
    private TipoSanguineoEnum tipoSanguineo;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Enumerated
    private SexoEnum genero;

}
