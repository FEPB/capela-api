package com.vxtech.capela.model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Table(name = "oficinas")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Builder(toBuilder=true)
@DynamicInsert
@DynamicUpdate
public class Oficina extends AbstractModel {

    private String descricao;
    private LocalDate data;
    private Integer vagas;

}
