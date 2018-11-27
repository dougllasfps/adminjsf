package org.dougllasfps.application.model;

/**
 * Criado por dougllas.sousa em 10/10/2018.
 */
public interface BaseEntity {

    Long getId();

    default boolean isEditable(){
        return true;
    }

    default void setEditable(boolean value){}

    default boolean isPersisted(){
        return getId() != null;
    }
}
