package org.dougllasfps.application.view.bean.generic;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.dougllasfps.application.model.BaseEntity;
import org.dougllasfps.application.service.generic.AbstractService;

import java.util.Optional;

import static org.dougllasfps.application.view.bean.util.JsfUtil.addSuccessToastMessage;

/**
 * Criado por dougllas.sousa em 10/10/2018.
 */

@Getter
@Setter
public abstract class AbstractFormBean<T extends BaseEntity, SERVICE extends AbstractService<T>> extends AbstractBean<T, SERVICE> {

    private String paramId;
    private String paramAction;

    private boolean readOnlyForm;

    protected static final String INSERT_ACTION  = "insert";
    protected static final String UPDATE_ACTION  = "update";
    protected static final String VIEW_ACTION    = "view";

    public void prepareView() {
        switch (paramAction) {
            case VIEW_ACTION:
                setReadOnlyForm(true);
            case UPDATE_ACTION:
                prepareUpdate();
                break;
            case INSERT_ACTION:
            default:
                prepareInsert();
                break;
        }
    }

    private void prepareUpdate() {
        Optional.ofNullable(getParamId()).ifPresent( id -> {
            T entity = getService().prepareEntityData(Long.valueOf(id));
            setEntity(entity);
        });
    }

    public void prepareInsert(){
        setEntity( getService().createInstanceOfEntityClass() );
    }

    public String save() {
        return doOnDefaultTryCatch(() -> {
            getService().save(getEntity());
            addSuccessToastMessage("Registro salvo com sucesso.");
        }, () -> getSearchFormLocation());
    }

    public String update() {
        return doOnDefaultTryCatch(() -> {
            getService().update(getEntity());
            addSuccessToastMessage("Registro atualizado com sucesso.");
        }, () -> getSearchFormLocation());
    }


    public String getSearchFormLocation(){
        return "";
    }

    public boolean isInserting(){
        return paramAction == null || INSERT_ACTION.equals(paramAction);
    }

    public boolean isUpdating(){
        return UPDATE_ACTION.equals(paramAction);
    }

    public boolean isViewing(){
        return VIEW_ACTION.equals(paramAction);
    }
}