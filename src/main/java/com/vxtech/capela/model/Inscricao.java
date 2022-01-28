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

    @Column(name = "trabalhador")
    private Boolean isTrabalhador;

    private String telefone;

    private String instituicao;

    private String email;

    @Column(name = "nome_coordenador")
    private String nomeCoordenador;

    @Column(name = "email_coordenador")
    private String emailCoordenador;

    @Column(name = "restricao_saude")
    private String restricaoSaude;

    @Column(name = "restricao_alimentar")
    private String restricaoAlimentar;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "oficina_id")
    private Oficina oficina;

}
