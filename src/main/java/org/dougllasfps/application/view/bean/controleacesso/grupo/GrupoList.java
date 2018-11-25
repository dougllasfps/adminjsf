package org.dougllasfps.application.view.bean.controleacesso.grupo;

import org.dougllasfps.application.model.controleacesso.Grupo;
import org.dougllasfps.application.service.GrupoService;
import org.dougllasfps.application.view.bean.generic.AbstractListBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 * Criado por dougllas.sousa em 09/10/2018.
 */

@Controller
@Scope("view")
public class GrupoList extends AbstractListBean<Grupo, GrupoService> {

}