package org.dougllasfps.application.view.bean.controleacesso.permissao;

import org.dougllasfps.application.model.controleacesso.ModuloPermissao;
import org.primefaces.model.DualListModel;

import java.util.ArrayList;
import java.util.List;

public class ModuloPermissaoDualList extends DualListModel<ModuloPermissao> {

    public ModuloPermissaoDualList(List<ModuloPermissao> source, List<ModuloPermissao> target) {
        super.setSource(source);
        super.setTarget(target);
    }

    public ModuloPermissaoDualList() {
        this(new ArrayList<>(), new ArrayList<>());
    }
}