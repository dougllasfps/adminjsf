package org.dougllasfps.application.view.bean.controleacesso.modulo.permissao;

import org.dougllasfps.application.model.controleacesso.Modulo;
import org.dougllasfps.application.service.ModuloService;
import org.dougllasfps.application.view.bean.generic.AbstractListBean;

import org.dougllasfps.application.configurations.scope.ViewScoped;
import javax.inject.Named;

/**
 * Criado por dougllas.sousa em 10/10/2018.
 */

@Named
@ViewScoped
public class ModuloList extends AbstractListBean<Modulo, ModuloService> {

	@Override
	public String getFormPageLocation() {
		return "/pages/controleacesso/modulo/modulo-form.xhtml";
	}
}