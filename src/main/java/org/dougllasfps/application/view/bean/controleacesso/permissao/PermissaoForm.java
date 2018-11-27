package org.dougllasfps.application.view.bean.controleacesso.permissao;

import lombok.Getter;
import lombok.Setter;
import org.dougllasfps.application.model.controleacesso.Modulo;
import org.dougllasfps.application.model.controleacesso.ModuloPermissao;
import org.dougllasfps.application.model.controleacesso.Permissao;
import org.dougllasfps.application.service.ModuloService;
import org.dougllasfps.application.service.PermissaoService;
import org.dougllasfps.application.view.bean.generic.AbstractFormBean;
import org.primefaces.model.DualListModel;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class PermissaoForm extends AbstractFormBean<Permissao,PermissaoService> {

    @Inject
    private ModuloService moduloService;

    @Getter@Setter
    private DualListModel<ModuloPermissao> moduloDualList;

    @Override
    public void prepareInsert() {
        super.prepareInsert();
        List<ModuloPermissao> source = moduloService.findAll().stream().map(m -> new ModuloPermissao(null, m, getEntity())).collect(Collectors.toList());
        moduloDualList = new ModuloPermissaoDualList(source, new ArrayList<>());
    }

    @Override
    public void prepareUpdate() {
        super.prepareUpdate();
        List<ModuloPermissao> source = moduloService.findAll().stream().map(m -> new ModuloPermissao(null, m, getEntity()))
                .collect(Collectors.toList());

        source.removeIf( mp -> getEntity().getModulos().stream().filter( mp2 -> mp2.getModulo().getId().equals(mp.getModulo().getId())).findAny().isPresent() );
        moduloDualList = new ModuloPermissaoDualList(source, getEntity().getModulos());
    }

    @Override
    public void executeSave() {
        getEntity().setModulos(getModuloDualList().getTarget());
        super.executeSave();
    }

    @Override
    public void executeUpdate() {
        getEntity().setModulos(getModuloDualList().getTarget());
        super.executeUpdate();
    }

    @Override
    public String getSearchFormLocation() {
        return "/pages/controleacesso/permissao/permissao-search";
    }

}
