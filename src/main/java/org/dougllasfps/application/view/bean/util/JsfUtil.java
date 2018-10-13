package org.dougllasfps.application.view.bean.util;

import com.sun.faces.component.visit.FullVisitContext;
import org.primefaces.context.RequestContext;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.application.ViewHandler;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.component.visit.VisitCallback;
import javax.faces.component.visit.VisitContext;
import javax.faces.component.visit.VisitResult;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import java.io.IOException;
import java.io.Serializable;

/**
 * Metodos  úteis para os managed beans.
 */
public class JsfUtil implements Serializable {

    public static void addWarnToastMessage(String message){
        RequestContext.getCurrentInstance().execute( ToastMessage.warn("Alerta", message ).getScript() );
    }

    public static void addSuccessToastMessage(String message){
        RequestContext.getCurrentInstance().execute( ToastMessage.success("Sucesso", message ).getScript() );
    }

    public static void addErrorToastMessage(String message){
        RequestContext.getCurrentInstance().execute( ToastMessage.error("Erro", message ).getScript() );
    }

    public static void addInfoToastMessage(String message){
        RequestContext.getCurrentInstance().execute( ToastMessage.info("Informação", message ).getScript() );
    }

    public static void addInfoMessage(String msg) {
        addInfoMessage(null, msg);
    }

    public static void addInfoMessage(String clientId, String msg) {
        addMessage( clientId,  FacesMessage.SEVERITY_INFO , msg  );
    }

    public static void addErrorMessage(String msg) {
        addErrorMessage( null, msg );
    }

    public static void addErrorMessage( String clientId, String msg ) {
        addMessage( clientId,  FacesMessage.SEVERITY_ERROR , msg  );
    }

    public static void addWarnMessage(String msg) {
        addWarnMessage(null, msg);
    }

    public static void addWarnMessage(String clientId, String msg) {
        addMessage( clientId,  FacesMessage.SEVERITY_WARN , msg  );
    }

    public static void addMessage(String clientId , Severity severityLevel , String message ){
        FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage( severityLevel , message, message  ) );
    }

    public FacesMessage getInfoFacesMessage( String message ){
        return new FacesMessage( FacesMessage.SEVERITY_INFO  , message, message  );
    }

    public FacesMessage getErrorFacesMessage( String message ){
        return new FacesMessage( FacesMessage.SEVERITY_ERROR  , message, message  );
    }

    public FacesMessage getWarnFacesMessage( String message ){
        return new FacesMessage( FacesMessage.SEVERITY_WARN  , message, message  );
    }

    public static void redirect(String pagina) throws IOException{
        ExternalContext externalContext = getExternalContext();
        Flash flash = externalContext.getFlash();
        flash.setKeepMessages(true);
        externalContext.redirect(pagina);
    }

    /**
     * Limpa os dados dos componentes de edição de seus filhos,
     * recursivamente. Checa se o componente instáncia de EditableValueHolder
     * e 'reseta' suas propriedades.
     * <p>
     * Quando este metodo, por algum motivo, não funcionar, parta para ignoráncia
     * e limpe o componente assim:
     * <p><blockquote><pre>
     * 	component.getChildren().clear()
     * </pre></blockquote>
     */
    public static void cleanSubmittedValues(UIComponent component) {
        if (component instanceof EditableValueHolder) {
            EditableValueHolder evh = (EditableValueHolder) component;
            evh.setSubmittedValue(null);
            evh.setValue(null);
            evh.setLocalValueSet(false);
            evh.setValid(true);
        }
        if(component.getChildCount()>0){
            for (UIComponent child : component.getChildren()) {
                cleanSubmittedValues(child);
            }
        }
    }

    /**
     * <p>
     *   Código disponível <a href ="https://cwiki.apache.org/confluence/display/MYFACES/Clear+Input+Components">aqui</a>.
     * </p>
     *
     */
    public static void refresh() {
        FacesContext context = FacesContext.getCurrentInstance();
        Application application = context.getApplication();
        ViewHandler viewHandler = application.getViewHandler();
        UIViewRoot viewRoot = viewHandler.createView(context, context
                .getViewRoot().getViewId());
        context.setViewRoot(viewRoot);
        context.renderResponse(); // Optional
    }

    public UIComponent findComponent(final String id) {

        FacesContext context = FacesContext.getCurrentInstance();
        UIViewRoot root = context.getViewRoot();
        final UIComponent[] found = new UIComponent[1];

        root.visitTree(new FullVisitContext(context), new VisitCallback() {
            @Override
            public VisitResult visit(VisitContext context, UIComponent component) {
                if(component.getId().equals(id)){
                    found[0] = component;
                    return VisitResult.COMPLETE;
                }
                return VisitResult.ACCEPT;
            }
        });

        return found[0];
    }

    public static void addFlashParam(String param, String value){
    	getExternalContext().getFlash().put(param, value);
    }
    
    public static Object getFlashParam(String param){
    	return getExternalContext().getFlash().get(param);
    }

	private static ExternalContext getExternalContext() {
		return FacesContext.getCurrentInstance().getExternalContext();
	}
	
	public static String getRequestParam(String param){
        return getExternalContext().getRequestParameterMap().get(param);
    }

    public static String addRequestParam(String param, String value){
        return getExternalContext().getRequestParameterMap().put(param,value);
    }

}