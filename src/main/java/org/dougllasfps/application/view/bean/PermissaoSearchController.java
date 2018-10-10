package org.dougllasfps.application.view.bean;

import org.dougllasfps.application.model.controleacesso.Permissao;
import org.dougllasfps.application.service.PermissaoService;
import org.dougllasfps.application.view.bean.generic.GenericSearchController;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 * Criado por dougllas.sousa em 10/10/2018.
 */

@Named
@ViewScoped
public class PermissaoSearchController extends GenericSearchController<Permissao, PermissaoService>{

}