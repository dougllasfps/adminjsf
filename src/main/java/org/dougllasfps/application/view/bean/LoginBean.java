package org.dougllasfps.application.view.bean;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
public class LoginBean implements Serializable {

    public void prepareView(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if( authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken) ){
            try {
                ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
                String contextName = externalContext.getRequestContextPath();
                externalContext.redirect(contextName + "/index.xhtml");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
