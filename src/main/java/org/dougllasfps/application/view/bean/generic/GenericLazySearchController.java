package org.dougllasfps.application.view.bean.generic;

import org.dougllasfps.application.model.BaseEntity;
import org.dougllasfps.application.service.generic.GenericService;

import javax.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.List;

import static org.dougllasfps.application.view.bean.util.JsfUtil.addWarnMessage;

/**
 * Criado por dougllas.sousa em 10/10/2018.
 */
public class GenericLazySearchController<T extends BaseEntity,S extends GenericService<T>> extends GenericSearchController<T, S> {

    private Long rowCount;
    private Integer rows;
    private Integer actualPage;

    public GenericLazySearchController(){
        setActualPage(0);
        setRows(10);
        setRowCount(0l);
    }

    @PostConstruct
    public void init(){
        super.init();
    }

    @Override
    public void find() {
        doOnDefaultTryCatch( () ->{
            validateSearch();
            Long count = getService().count(getEntity());
            setRowCount(count);
            loadActualPage();
            if(getResult().isEmpty()){
                addWarnMessage("Nenhum registro encontrado.");
            }
        });
    }

    public void goToPage(Integer pageNumber){
        setActualPage(pageNumber - 1);
        loadActualPage();
    }

    public void first(){
        goToPage(1);
    }

    public void last(){
        goToPage(getTotalPages().intValue());
    }

    public void foward(){
        goToPage(getActualPage()+1);
    }

    private void loadActualPage() {
        this.result = getService().load( getActualPage(), getRows(), null, getEntity() );
    }

    public void back(){
        goToPage(getActualPage() - 1);
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows( Integer rows ) {
        this.rows = rows;
    }

    public Integer getActualPage() {
        return actualPage;
    }

    protected void setActualPage( Integer actualPage ) {
        this.actualPage = actualPage;
    }

    protected void setRowCount( Long rowCount ) {
        this.rowCount = rowCount;
    }

    protected Long getRowCount() {
        return rowCount;
    }

    public Long getTotalPages(){
        long value = getRowCount() / getRows();
        long mod   = getRowCount() % Long.valueOf(getRows());

        if(mod > 0){
            value++;
        }

        return value;
    }

    public List<Integer> getPagesRange(){
        List<Integer> values = new ArrayList<>();
        for(int x = 1; x <= getTotalPages(); x++){
            values.add(x);
        }
        return values;
    }
}