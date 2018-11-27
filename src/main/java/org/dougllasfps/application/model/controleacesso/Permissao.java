package org.dougllasfps.application.model.controleacesso;

import lombok.*;
import org.dougllasfps.application.model.BaseEntity;
//import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

/**
 * Criado por dougllas.sousa em 09/10/2018.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table( name = "permissao",  schema = "controle_acesso")
public class Permissao implements  BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "label")
    private String label;

    @OneToMany(mappedBy = "modulo")
    private List<ModuloPermissao> modulos;

}