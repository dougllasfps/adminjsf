package org.dougllasfps.application.view.bean.controleacesso.permissao;

import org.dougllasfps.application.model.controleacesso.Permissao;
import org.dougllasfps.application.service.PermissaoService;
import org.dougllasfps.application.view.bean.generic.AbstractFormBean;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class PermissaoForm extends AbstractFormBean<Permissao,PermissaoService> {

    @Override
    public String getSearchFormLocation() {
        return "/pages/controleacesso/permissao/permissao-search";
    }
}
