package org.dougllasfps.application.view.bean.generic;

import org.dougllasfps.application.model.BaseEntity;
import org.dougllasfps.application.service.generic.GenericService;
import org.dougllasfps.application.view.bean.util.JsfUtil;

import javax.annotation.PostConstruct;
import java.util.Optional;

/**
 * Criado por dougllas.sousa em 10/10/2018.
 */
public abstract class GenericFormController<T extends BaseEntity, SERVICE extends GenericService<T>> extends GenericController<T, SERVICE> {

    private Optional<Long> idEntidade;

    public GenericFormController(){
        this.idEntidade = Optional.empty();
    }

    @PostConstruct
    public void init(){

        String idString = (String) JsfUtil.getFlashParam(ID_PARAM);

        Optional.ofNullable(idString).ifPresent( i ->{
            Long id = Long.valueOf(i);
            setIdEntidade( Optional.of(id) );
        });

        prepareForm();
    }

    public void prepareForm() {
        if (getIdEntidade().isPresent()) {
            T entity = getService().prepareEntityData(getIdEntidade().get());
            setEntity(entity);
        } else {
            setEntity( getService().createInstanceOfEntityClass() );
            prepareInsert();
        }
    }

    public void prepareInsert(){}

    public void save() {
        doOnDefaultTryCatch(() -> {
            getService().save(getEntity());
            JsfUtil.addInfoMessage("Registro salvo com sucesso.");
        });
    }

    public void update() {
        doOnDefaultTryCatch(() -> {
            getService().update(getEntity());
            JsfUtil.addInfoMessage("Registro atualizado com sucesso.");
        });
    }

    public Optional<Long> getIdEntidade() {
        return idEntidade;
    }

    public void setIdEntidade(Optional<Long> idEntidade) {
        this.idEntidade = idEntidade;
    }
}