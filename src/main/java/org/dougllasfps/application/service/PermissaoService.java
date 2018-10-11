package org.dougllasfps.application.service;

import org.dougllasfps.application.exception.ValidationException;
import org.dougllasfps.application.model.controleacesso.Permissao;
import org.dougllasfps.application.repository.PermissaoRepository;
import org.dougllasfps.application.service.generic.impl.GenericServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * Criado por dougllas.sousa em 10/10/2018.
 */

@Service
public class PermissaoService extends GenericServiceImpl<Permissao, PermissaoRepository> implements Serializable{

    @Override
    public void validateSave(Permissao permissao) {

        ValidationException e = new ValidationException();

        if( permissao.getLabel() == null ){
            e.addError("Campo label é obrigatório.");
        }

        if( permissao.getDescricao() == null ){
            e.addError("Campo Descrição é obrigatório.");
        }

        e.dispatch();

    }
}