package org.dougllasfps.application.view.bean.generic;

import org.dougllasfps.application.model.BaseEntity;
import org.dougllasfps.application.service.generic.AbstractService;

import javax.annotation.PostConstruct;
import java.util.List;

import static org.dougllasfps.application.view.bean.util.JsfUtil.*;

/**
 * Criado por dougllas.sousa em 10/10/2018.
 */
public abstract class AbstractListBean<T extends BaseEntity, S extends AbstractService<T>> extends AbstractBean<T, S> {

    protected List<T> result;

    @PostConstruct
    public void init(){
        T instanceOfEntityClass = getService().createInstanceOfEntityClass();
        setEntity(instanceOfEntityClass);
    }

    public void validateSearch(){}

    public void find(){
        doOnDefaultTryCatch( () ->{
            validateSearch();
            this.result = getService().find(getEntity());
            if(getResult().isEmpty()){
                addWarnToastMessage("Nenhum registro encontrado.");
            }
        });
    }

    public void delete(){
        doOnDefaultTryCatch( ()->{
            getService().delete(getEntity());
            addSuccessToastMessage("Registro removido com sucesso.");
            getResult().remove(getEntity());
            setEntity(getService().createInstanceOfEntityClass());
        });
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public String sendToForm(T entity){
    	addFlashParam("id", entity.getId().toString());
        return getFormPageLocation();
    }

    public void prepareDelete(T entity){
        setEntity(entity);
    }
    
    public String novo() {
    	return getFormPageLocation();
    }

    public String getFormPageLocation(){
        return "";
    }
}