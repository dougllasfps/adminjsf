package org.dougllasfps.application.repository;

import org.dougllasfps.application.model.controleacesso.ModuloPermissao;
import org.dougllasfps.application.model.controleacesso.Permissao;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface ModuloPermissaoRepository extends FullRepository<ModuloPermissao> {

    void deleteModuloPermissaoByPermissaoAndIdNotIn(Permissao permissao, Iterable<Long> ids);

    List<ModuloPermissao> findAllByPermissao(Permissao permissao);
}
