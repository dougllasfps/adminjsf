package org.dougllasfps.application.exception;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Criado por dougllas.sousa em 10/10/2018.
 */
public class ValidationException extends RuntimeException {

    private List<String> errors;

    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public ValidationException() {
        this.errors = new ArrayList<>();
    }

    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public ValidationException(List<String> errors) {
        this.errors = errors;
    }

    public void addError(String message){
        this.errors.add(message);
    }

    public List<ApplicationException> getErrors(){
        List<ApplicationException> exceptions = new ArrayList<>();
        this.errors.forEach(e -> exceptions.add(new ApplicationException(e)));
        return exceptions;
    }

    public boolean hasErrors(){
        return !CollectionUtils.isEmpty(this.errors);
    }

    public void throwIfHasErrors(){
        if(hasErrors()){
            throw this;
        }
    }

    @Override
    public String getMessage() {
        return this.errors.toString();
    }
}
