package org.dougllasfps.application.view.bean.generic;

import org.dougllasfps.application.model.BaseEntity;
import org.dougllasfps.application.service.generic.GenericService;
import org.dougllasfps.application.view.bean.util.JsfUtil;

import javax.annotation.PostConstruct;
import java.util.Optional;

import static org.dougllasfps.application.view.bean.util.JsfUtil.addSuccessToastMessage;

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

    public String save() {
        doOnDefaultTryCatch(() -> {
            getService().save(getEntity());
            addSuccessToastMessage("Registro salvo com sucesso.");
        });
        return getSearchFormLocation();
    }

    public String update() {
        doOnDefaultTryCatch(() -> {
            getService().update(getEntity());
            addSuccessToastMessage("Registro atualizado com sucesso.");
        });
        return getSearchFormLocation();
    }

    public Optional<Long> getIdEntidade() {
        return idEntidade;
    }

    public void setIdEntidade(Optional<Long> idEntidade) {
        this.idEntidade = idEntidade;
    }

    public String getSearchFormLocation(){
        return "";
    }
}