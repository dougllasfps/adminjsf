package org.dougllasfps.application.view.bean.generic;

import org.dougllasfps.application.model.BaseEntity;
import org.dougllasfps.application.service.generic.GenericService;
import org.dougllasfps.application.view.bean.util.JsfUtil;

import javax.annotation.PostConstruct;
import java.util.List;

import static org.dougllasfps.application.view.bean.util.JsfUtil.addInfoMessage;
import static org.dougllasfps.application.view.bean.util.JsfUtil.addWarnMessage;
import static org.dougllasfps.application.view.bean.util.JsfUtil.doOnDefaultTryCatch;

/**
 * Criado por dougllas.sousa em 10/10/2018.
 */
public class GenericSearchController<T extends BaseEntity, S extends GenericService<T>> extends GenericController<T, S> {

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
                addWarnMessage("Nenhum registro encontrado.");
            }
        });
    }

    public void delete(){
        doOnDefaultTryCatch( ()->{
            getService().delete(getEntity());
            addInfoMessage("Registro removido com sucesso.");
        });
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public String prepareFormToViewOrUpdate(T entity){
    	JsfUtil.addFlashParam("id", entity.getId().toString());
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