package org.dougllasfps.application.view.bean;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collection;

@Named
@RequestScoped
public class AuthBean implements Serializable {

    public boolean isAuthenticated(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken);
    }

    public boolean hasAllRoles(String roles){
        if(roles == null){
            return false;
        }

        String[] split = roles.split(",");
        for (String role : split) {
            if(!hasRole(role.trim())){
                return false;
            }
        }
        return true;
    }

    public boolean hasRole(String role){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            if(authority.getAuthority().equals(role)){
                return true;
            }
        }

        return false;
    }

    public boolean hasAnyRole(String roles){
        if(roles == null){
            return false;
        }

        String[] split = roles.split(",");

        for (String role : split) {
            if(hasRole(role.trim())){
                return true;
            }
        }
        return false;
    }
}
