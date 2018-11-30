//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.dougllasfps.application.configurations;

import lombok.Generated;

import java.lang.annotation.Annotation;

public class AnnotationToScopeMapping {
    private final Class<? extends Annotation> annotation;
    private final String scope;

    @Generated
    public Class<? extends Annotation> getAnnotation() {
        return this.annotation;
    }

    @Generated
    public String getScope() {
        return this.scope;
    }

    @Generated
    public AnnotationToScopeMapping(Class<? extends Annotation> annotation, String scope) {
        this.annotation = annotation;
        this.scope = scope;
    }
}
