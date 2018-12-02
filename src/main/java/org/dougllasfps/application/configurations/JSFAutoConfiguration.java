package org.dougllasfps.application.configurations;

import com.sun.faces.config.ConfigureListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;

@Configuration
public class JSFAutoConfiguration implements ServletContextAware, WebMvcConfigurer {

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        FacesServlet servlet = new FacesServlet();
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean( servlet, "*.xhtml" );
        return servletRegistrationBean;
    }

    @Bean
    public ServletListenerRegistrationBean<ConfigureListener> jsfConfigureListener() {
        return new ServletListenerRegistrationBean(new ConfigureListener());
    }

    @Override
    public void setServletContext( ServletContext servletContext ) {

        servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());
        servletContext.setInitParameter("javax.faces.FACELETS_SKIP_COMMENTS", "true");

        servletContext.setInitParameter("javax.faces.DATETIMECONVERTER_DEFAULT_TIMEZONE_IS_SYSTEM_TIMEZONE" , "true" );
        servletContext.setInitParameter("javax.faces.INTERPRET_EMPTY_STRING_SUBMITTED_VALUES_AS_NULL" , "false" );
        servletContext.setInitParameter("com.sun.faces.allowTextChildren" , "true" );
        servletContext.setInitParameter("com.sun.faces.enableMissingResourceLibraryDetection" , "false" );
        servletContext.setInitParameter("com.sun.faces.expressionFactory", "com.sun.el.ExpressionFactoryImpl");

        //http://balusc.omnifaces.org/2015/09/what-mojarra-context-parameters-are.html
        servletContext.setInitParameter("com.sun.faces.enableLazyBeanValidation", "false");
        servletContext.setInitParameter("com.sun.faces.displayConfiguration", "true");
        servletContext.setInitParameter("com.sun.faces.disableIdUniquenessCheck", "true");

        servletContext.setInitParameter("primefaces.THEME" , "bootstrap");
        servletContext.setInitParameter("primefaces.UPLOADER" , "native" );
    }

    /**
     * página inicial
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController( "/" ).setViewName( "forward:/index.xhtml" );
        registry.setOrder( Ordered.HIGHEST_PRECEDENCE );
    }
}
