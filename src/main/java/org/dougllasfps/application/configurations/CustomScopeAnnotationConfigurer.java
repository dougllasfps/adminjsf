//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.dougllasfps.application.configurations;

import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.Ordered;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.MethodMetadata;

import java.lang.annotation.Annotation;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CustomScopeAnnotationConfigurer implements BeanFactoryPostProcessor, Ordered {
    @Generated
    private static final Logger log = LoggerFactory.getLogger(CustomScopeAnnotationConfigurer.class);
    private int order = 2147483647;
    private List<AnnotationToScopeMapping> annotationToScopeMappings;

    public void postProcessBeanFactory(ConfigurableListableBeanFactory clbf) throws BeansException {
        String[] var2 = clbf.getBeanDefinitionNames();
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            String beanName = var2[var4];
            BeanDefinition definition = clbf.getBeanDefinition(beanName);
            this.registerJsfCdiToSpring(definition);
        }

    }

    private void registerJsfCdiToSpring(BeanDefinition definition) {
        if(definition instanceof AnnotatedBeanDefinition) {
            AnnotatedBeanDefinition annDef = (AnnotatedBeanDefinition)definition;
            String scopeName = null;
            if(annDef.getFactoryMethodMetadata() != null) {
                scopeName = this.deduceScopeName(annDef.getFactoryMethodMetadata());
            } else {
                scopeName = this.deduceScopeName(annDef.getMetadata());
            }

            if(scopeName != null) {
                definition.setScope(scopeName);
                log.debug("{} - Scope({})", definition.getBeanClassName(), scopeName.toUpperCase());
            }
        }

    }

    protected String deduceScopeName(MethodMetadata factoryMethodMetadata) {
        if(this.getAnnotationToScopeMappings() == null) {
            return null;
        } else {
            Iterator var2 = this.getAnnotationToScopeMappings().iterator();

            AnnotationToScopeMapping annotationToScopeMapping;
            do {
                if(!var2.hasNext()) {
                    return null;
                }

                annotationToScopeMapping = (AnnotationToScopeMapping)var2.next();
            } while(!factoryMethodMetadata.isAnnotated(annotationToScopeMapping.getAnnotation().getName()));

            return annotationToScopeMapping.getScope();
        }
    }

    protected String deduceScopeName(AnnotationMetadata classMetadata) {
        if(classMetadata != null && this.getAnnotationToScopeMappings() != null) {
            Iterator var2 = this.getAnnotationToScopeMappings().iterator();

            AnnotationToScopeMapping annotationToScopeMapping;
            do {
                if(!var2.hasNext()) {
                    return null;
                }

                annotationToScopeMapping = (AnnotationToScopeMapping)var2.next();
            } while(!classMetadata.hasAnnotation(annotationToScopeMapping.getAnnotation().getName()));

            return annotationToScopeMapping.getScope();
        } else {
            return null;
        }
    }

    public void addMapping(Class<? extends Annotation> annotation, String scopeName) {
        this.addMapping(new AnnotationToScopeMapping(annotation, scopeName));
    }

    public void addMapping(AnnotationToScopeMapping annotationToScopeMapping) {
        if(this.annotationToScopeMappings == null) {
            this.annotationToScopeMappings = new LinkedList();
        }

        this.annotationToScopeMappings.add(annotationToScopeMapping);
    }

    @Generated
    public List<AnnotationToScopeMapping> getAnnotationToScopeMappings() {
        return this.annotationToScopeMappings;
    }

    @Generated
    public void setAnnotationToScopeMappings(List<AnnotationToScopeMapping> annotationToScopeMappings) {
        this.annotationToScopeMappings = annotationToScopeMappings;
    }

    @Generated
    public CustomScopeAnnotationConfigurer() {
    }

    @Generated
    public int getOrder() {
        return this.order;
    }

    @Generated
    public void setOrder(int order) {
        this.order = order;
    }
}
