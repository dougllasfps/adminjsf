package org.dougllasfps.application.view.bean.generic;

import org.dougllasfps.application.service.generic.GenericService;

import javax.inject.Inject;

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
}