package org.dougllasfps.application.view.bean.controleacesso.modulo.permissao;

import org.dougllasfps.application.model.controleacesso.Modulo;
import org.dougllasfps.application.model.controleacesso.Permissao;
import org.dougllasfps.application.service.ModuloService;
import org.dougllasfps.application.service.PermissaoService;
import org.dougllasfps.application.view.bean.generic.AbstractFormBean;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class ModuloForm extends AbstractFormBean<Modulo, ModuloService> {

    @Override
    public String getSearchFormLocation() {
        return "/pages/controleacesso/modulo/modulo-search";
    }
}
