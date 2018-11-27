package org.dougllasfps.application.service;

import org.dougllasfps.application.exception.ValidationException;
import org.dougllasfps.application.model.controleacesso.ModuloPermissao;
import org.dougllasfps.application.model.controleacesso.Permissao;
import org.dougllasfps.application.repository.ModuloPermissaoRepository;
import org.dougllasfps.application.repository.PermissaoRepository;
import org.dougllasfps.application.service.generic.impl.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.dougllasfps.application.util.Asserts.invalidString;

/**
 * Criado por dougllas.sousa em 10/10/2018.
 */

@Service
public class PermissaoService extends AbstractServiceImpl<Permissao, PermissaoRepository> implements Serializable{

    @Inject
    private ModuloPermissaoRepository moduloPermissaoRepository;

    @Override
    public void validateSave(Permissao permissao) {

        ValidationException e = new ValidationException();

        if(invalidString(permissao.getLabel()) ){
            e.addError("Campo label é obrigatório.");
        }

        if( invalidString(permissao.getDescricao()) ){
            e.addError("Campo Descrição é obrigatório.");
        }

        if(getRepository().existsByLabel(permissao.getLabel())){
            e.addError("Label já cadastrado anteriomente.");
        }

        if(getRepository().existsByDescricao(permissao.getDescricao())){
            e.addError("Descrição já cadastrada anteriomente.");
        }

        if(CollectionUtils.isEmpty(permissao.getModulos())){
            e.addError("Selecione pelo menos um módulo.");
        }

        e.throwIfHasErrors();

    }

    @Override
    public Permissao save(Permissao permissao) {
        moduloPermissaoRepository.saveAll(permissao.getModulos());
        return super.save(permissao);
    }

    @Override
    public Permissao update(Permissao permissao) {
        moduloPermissaoRepository.saveAll(permissao.getModulos());
        moduloPermissaoRepository.deleteModuloPermissaoByPermissaoAndIdNotIn(permissao, permissao.getModulos().stream().map(ModuloPermissao::getId).collect(Collectors.toList()));
        return super.update(permissao);
    }

    @Override
    public Permissao prepareEntityData(Long id) {
        return find(id).map( p -> {
            List<ModuloPermissao> list = moduloPermissaoRepository.findAllByPermissao(p);
            p.setModulos(list);
            return p;
        }).orElse(null);
    }
}