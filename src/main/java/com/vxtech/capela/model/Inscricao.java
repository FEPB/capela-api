package com.vxtech.capela.model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Table(name = "inscricoes")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Builder(toBuilder=true)
@DynamicInsert
@DynamicUpdate
public class Inscricao extends AbstractModel {

    @Column(name = "confirmado")
    private Boolean isConfirmada;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Pessoa pessoa;

}
