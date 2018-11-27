package org.dougllasfps.application.view.bean.controleacesso.modulo.permissao;

import org.dougllasfps.application.model.controleacesso.Modulo;
import org.dougllasfps.application.model.controleacesso.Permissao;
import org.dougllasfps.application.service.ModuloService;
import org.dougllasfps.application.service.PermissaoService;
import org.dougllasfps.application.view.bean.generic.AbstractListBean;

import javax.faces.view.ViewScoped;
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