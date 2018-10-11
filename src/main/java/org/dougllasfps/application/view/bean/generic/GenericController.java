package org.dougllasfps.application.view.bean.generic;

import org.dougllasfps.application.exception.ApplicationException;
import org.dougllasfps.application.exception.ValidationException;
import org.dougllasfps.application.service.generic.GenericService;
import org.dougllasfps.application.view.bean.Action;

import javax.inject.Inject;

import static org.dougllasfps.application.view.bean.util.JsfUtil.addErrorMessage;

/**
 * Criado por dougllas.sousa em 10/10/2018.
 */

public abstract class GenericController<T, SERVICE extends GenericService> {

    @Inject
    private SERVICE service;

    public static final String ID_PARAM = "id";

    private T entity;

    public SERVICE getService() {
        return service;
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public void doOnDefaultTryCatch(Action action){
        try {
            action.execute();
        } catch (ValidationException e) {
            e.getErrors().forEach( err -> addErrorMessage(err.getMessage()) );
        } catch (ApplicationException e) {
            addErrorMessage(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}