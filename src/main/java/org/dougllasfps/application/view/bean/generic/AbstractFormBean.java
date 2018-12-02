package org.dougllasfps.application.view.bean.generic;

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

    protected static final String INSERT_ACTION  = "insert";
    protected static final String UPDATE_ACTION  = "update";
    protected static final String VIEW_ACTION    = "view";

    public void prepareView() {
        if(paramAction ==  null) {
            prepareInsert();
        }else{
            switch (paramAction) {
                case VIEW_ACTION:
                    prepareUpdate();
                    getEntity().setEditable(false);
                    break;
                case UPDATE_ACTION:
                    prepareUpdate();
                    break;
                case INSERT_ACTION:
                default:
                    prepareInsert();
                    break;
            }
        }

    }

    public void prepareUpdate() {
        T entity = Optional.ofNullable(getParamId()).map(id -> getService().prepareEntityData(Long.valueOf(id))).orElse(getService().createInstanceOfEntityClass());
        setEntity(entity);
    }

    public void prepareInsert(){
        setEntity( getService().createInstanceOfEntityClass() );
    }

    public String save() {
        return doOnDefaultTryCatch(() -> {
            executeSave();
        }, () -> getSearchFormLocation());
    }

    public void executeSave() {
        getService().save(getEntity());
        addSuccessToastMessage("Registro salvo com sucesso.");
    }

    public String update() {
        return doOnDefaultTryCatch(() -> {
            executeUpdate();
        }, () -> getSearchFormLocation());
    }

    public void executeUpdate() {
        getService().update(getEntity());
        addSuccessToastMessage("Registro atualizado com sucesso.");
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