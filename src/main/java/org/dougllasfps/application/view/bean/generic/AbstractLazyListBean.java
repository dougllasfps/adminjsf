package org.dougllasfps.application.view.bean.generic;

import org.dougllasfps.application.model.BaseEntity;
import org.dougllasfps.application.service.generic.AbstractService;
import org.dougllasfps.application.view.bean.component.LazyDataTable;

import javax.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.List;

import static org.dougllasfps.application.view.bean.util.JsfUtil.addWarnMessage;

/**
 * Criado por dougllas.sousa em 10/10/2018.
 */
public abstract class AbstractLazyListBean<T extends BaseEntity,S extends AbstractService<T>> extends AbstractListBean<T, S> {

    private LazyDataTable<T> dataTable;

    @PostConstruct
    public void init(){
        super.init();
        this.dataTable = new LazyDataTable<>(getService(), getEntity());
    }

    @Override
    public void find() {
        doOnDefaultTryCatch( () ->{
            validateSearch();
            getDataTable().find();
            if(getDataTable().isEmpty()){
                addWarnMessage("Nenhum registro encontrado.");
            }
        });
    }

    public LazyDataTable<T> getDataTable() {
        return dataTable;
    }

    public void setDataTable(LazyDataTable<T> dataTable) {
        this.dataTable = dataTable;
    }
}