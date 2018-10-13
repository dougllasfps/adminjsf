package org.dougllasfps.application.view.bean.controleacesso.permissao;

import org.dougllasfps.application.model.controleacesso.Permissao;
import org.dougllasfps.application.service.PermissaoService;
import org.dougllasfps.application.view.bean.generic.AbstractLazyListBean;
import org.dougllasfps.application.view.bean.generic.AbstractListBean;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 * Criado por dougllas.sousa em 10/10/2018.
 */

@Named
@ViewScoped
public class PermissaoList extends AbstractListBean<Permissao, PermissaoService> {

	@Override
	public String getFormPageLocation() {
		return "/pages/controleacesso/permissao/permissao-form";
	}
}