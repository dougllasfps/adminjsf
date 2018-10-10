package org.dougllasfps.application.view.bean.generic;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Criado por dougllas.sousa em 10/10/2018.
 */
public class GenericLazySearchControllerTest {

    GenericLazySearchController bean;

    @Before
    public void setup(){
        bean = new GenericLazySearchController();
    }

    @Test
    public void deveRetornarQuantiadePaginas(){
        bean.setRowCount(1l);
        Long totalPages = bean.getTotalPages();
        Assert.assertEquals(totalPages.longValue(), 1l);
    }

    @Test
    public void deveIrParaProximaPagina(){
        bean.setRowCount(1l);
        bean.foward();
        Assert.assertEquals(bean.getActualPage().intValue(), 1);
    }
}
