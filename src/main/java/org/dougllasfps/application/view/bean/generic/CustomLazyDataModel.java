package org.dougllasfps.application.view.bean.generic;

import org.dougllasfps.application.service.generic.DemandPaginationService;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Objects;

/**
 * Criado por dougllas.sousa em 10/10/2018.
 */
public class CustomLazyDataModel<T> {

    private int actual;
    private int rows;
    private DemandPaginationService<T> paginationService;
    private List<T> content;
    private T filter;
    private Sort sortOptions;

    public CustomLazyDataModel( DemandPaginationService<T> paginationService, T filter, int rows ) {
        Objects.requireNonNull(paginationService);
        this.paginationService = paginationService;

        if(rows < 1){
            throw new IllegalArgumentException("rows must be greater than 0");
        }

        this.actual = 0;
        this.rows = rows;
        this.filter = filter;
    }

    public int getActual() {
        return actual;
    }

    public void setActual(int actual) {
        this.actual = actual;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    protected void toPage(int page){
        setActual(page);
        loadContent();
    }

    protected void loadContent() {
        this.content = paginationService.load( getActual(), rows, getSortOptions(), filter );
    }

    protected Sort getSortOptions() {
        return sortOptions;
    }

    public void setSortOptions(Sort sortOptions) {
        this.sortOptions = sortOptions;
    }
}