package org.dougllasfps.application.view.bean.controleacesso.modulo.permissao;

import org.dougllasfps.application.configurations.scope.ViewScoped;
import org.dougllasfps.application.model.controleacesso.Modulo;
import org.dougllasfps.application.service.ModuloService;
import org.dougllasfps.application.view.bean.generic.AbstractFormBean;

import javax.inject.Named;

@Named
@ViewScoped
public class ModuloForm extends AbstractFormBean<Modulo, ModuloService> {

    @Override
    public String getSearchFormLocation() {
        return "/pages/controleacesso/modulo/modulo-search";
    }
}
