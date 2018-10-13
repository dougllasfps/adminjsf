package org.dougllasfps.application.view.bean.component;

import org.dougllasfps.application.service.generic.DemandPaginationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LazyDataTable<T> {

    private DemandPaginationService<T> demandPaginationService;
    private List<T> content;
    private T model;
    private Long rowCount;
    private Integer rows;
    private Integer actualPage;

    public LazyDataTable(DemandPaginationService<T> demandPaginationService, T model) {
        Objects.requireNonNull(demandPaginationService);
        Objects.requireNonNull(model);
        this.demandPaginationService = demandPaginationService;
        this.model = model;
    }

    public List<T> getContent() {
        return content;
    }

    public void find(){
        Long count = demandPaginationService.count(model);
        setRowCount(count);
        setActualPage(0);
        executeLoad();
    }

    public void executeLoad(){
        this.content = demandPaginationService.load(actualPage, rows, null, model);
    }

    public T getModel() {
        return model;
    }

    public void setModel(T model) {
        this.model = model;
    }

    public boolean isCurrentPage(Integer pageNumber){
        return pageNumber != null && pageNumber.equals(getActualPage() + 1);
    }

    public void goToPage(Integer pageNumber){
        setActualPage(pageNumber - 1);
        executeLoad();
    }

    public void first(){
        goToPage(1);
    }

    public void last(){
        goToPage(getTotalPages().intValue());
    }

    public void foward(){
        goToPage(getActualPage()+2);
    }

    public void back(){
        goToPage(getActualPage());
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

    public boolean hasPrevious(){
        return getActualPage() > 0;
    }

    public boolean hasNext(){
        return getActualPage() < getTotalPages() - 1;
    }

    public boolean isEmpty(){
        return content == null || content.isEmpty();
    }
}
