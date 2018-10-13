package org.dougllasfps.application.service;

import org.dougllasfps.application.exception.ValidationException;
import org.dougllasfps.application.model.controleacesso.Permissao;
import org.dougllasfps.application.repository.PermissaoRepository;
import org.dougllasfps.application.service.generic.impl.AbstractServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;

import static org.dougllasfps.application.util.Asserts.invalidString;

/**
 * Criado por dougllas.sousa em 10/10/2018.
 */

@Service
public class PermissaoService extends AbstractServiceImpl<Permissao, PermissaoRepository> implements Serializable{

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

        e.throwIfHasErrors();

    }
}