package org.dougllasfps.application;

import com.sun.faces.config.ConfigureListener;
import org.dougllasfps.application.configurations.CustomScopeAnnotationConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.faces.bean.*;
import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;

@SpringBootApplication
public class Application extends SpringBootServletInitializer implements ServletContextAware, WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        FacesServlet servlet = new FacesServlet();
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(servlet, "*.xhtml");
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
        servletContext.setInitParameter("com.sun.faces.enableMissingResourceLibraryDetection" , "native" );
        servletContext.setInitParameter("com.sun.faces.expressionFactory", "com.sun.el.ExpressionFactoryImpl");

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

    @Bean
    public static CustomScopeAnnotationConfigurer jsfScopeAnnotationsConfigurer(Environment environment) {
        CustomScopeAnnotationConfigurer scopeAnnotationConfigurer = new CustomScopeAnnotationConfigurer();
        scopeAnnotationConfigurer.addMapping(NoneScoped.class, "prototype");
        scopeAnnotationConfigurer.addMapping(RequestScoped.class, "request");
        scopeAnnotationConfigurer.addMapping(ViewScoped.class, "view");
        scopeAnnotationConfigurer.addMapping(javax.faces.view.ViewScoped.class, "view");
        scopeAnnotationConfigurer.addMapping(SessionScoped.class, "session");
        scopeAnnotationConfigurer.addMapping(ApplicationScoped.class, "application");
        return scopeAnnotationConfigurer;
    }
}
