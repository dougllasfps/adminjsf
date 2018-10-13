package org.dougllasfps.application.view.bean.generic;

import org.dougllasfps.application.exception.ApplicationException;
import org.dougllasfps.application.exception.ValidationException;
import org.dougllasfps.application.service.generic.AbstractService;
import org.dougllasfps.application.view.bean.Action;

import javax.inject.Inject;

import java.util.function.Supplier;

import static org.dougllasfps.application.view.bean.util.JsfUtil.addErrorToastMessage;

/**
 * Criado por dougllas.sousa em 10/10/2018.
 */

public abstract class AbstractBean<T, SERVICE extends AbstractService> {

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
        doOnDefaultTryCatch(action, () -> null);
    }

    public <T> T doOnDefaultTryCatch(Action action, Supplier<T> callBack){
        try {
            action.execute();
            return callBack.get();
        } catch (ValidationException e) {
            e.getErrors().forEach( err -> addErrorToastMessage(err.getMessage()) );
        } catch (ApplicationException e) {
            addErrorToastMessage(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            addErrorToastMessage("Ocorreu um erro inesperado. Caso o erro persista, entre em contato com a Administração.");
        }
        return null;
    }
}