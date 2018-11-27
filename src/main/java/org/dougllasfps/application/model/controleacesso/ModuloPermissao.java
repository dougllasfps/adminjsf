package org.dougllasfps.application.model.controleacesso;

import lombok.*;
import org.dougllasfps.application.model.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode(exclude = "id", doNotUseGetters = true)
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(schema = "controle_acesso", name = "modulo_permissao")
public class ModuloPermissao implements Serializable, BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cd_modulo")
    private Modulo modulo;

    @ManyToOne
    @JoinColumn(name = "cd_permissao")
    private Permissao permissao;


}
