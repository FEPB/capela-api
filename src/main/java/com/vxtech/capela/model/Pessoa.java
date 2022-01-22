package com.vxtech.capela.model;

import com.vxtech.capela.enums.SexoEnum;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Table(name = "pessoas")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Builder(toBuilder=true)
public class Pessoa extends AbstractModel {

    private String nome;
    private LocalDateTime dataNascimento;

    @Enumerated
    private SexoEnum genero;

}
