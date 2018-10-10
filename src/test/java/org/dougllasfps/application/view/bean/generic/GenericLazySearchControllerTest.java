package org.dougllasfps.application.view.bean.generic;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

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
    public void deveTrazerRangeApropriado(){
        bean.setRowCount(1l);
        List<Integer> pagesRange = bean.getPagesRange();
        Assert.assertEquals(pagesRange, Arrays.asList(1));
    }


}
