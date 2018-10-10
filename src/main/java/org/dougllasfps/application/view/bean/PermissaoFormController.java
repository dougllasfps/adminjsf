package org.dougllasfps.application.view.bean;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.dougllasfps.application.model.controleacesso.Permissao;
import org.dougllasfps.application.service.PermissaoService;
import org.dougllasfps.application.view.bean.generic.GenericFormController;

@Named
@ViewScoped
public class PermissaoFormController extends GenericFormController<Permissao,PermissaoService> {

}
