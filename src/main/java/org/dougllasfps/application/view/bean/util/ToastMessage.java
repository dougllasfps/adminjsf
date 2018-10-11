package org.dougllasfps.application.view.bean.util;

/**
 * Criado por dougllas.sousa em 11/10/2018.
 */
public class ToastMessage {

    private String title;
    private String message;
    private ToastSeverity severity;

    public static ToastMessage error(String title, String message){
        return new ToastMessage(title, message, ToastSeverity.error);
    }

    public static ToastMessage info(String title, String message){
        return new ToastMessage(title, message, ToastSeverity.info);
    }

    public static ToastMessage warn(String title, String message){
        return new ToastMessage(title, message, ToastSeverity.warning);
    }

    public static ToastMessage success(String title, String message){
        return new ToastMessage(title, message, ToastSeverity.success);
    }

    public static ToastMessage of(String title, String message, ToastSeverity severity){
        return new ToastMessage(title, message, severity);
    }

    private ToastMessage(String title, String message, ToastSeverity severity) {
        this.title = title;
        this.message = message;
        this.severity = severity;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ToastSeverity getSeverity() {
        return severity;
    }

    public void setSeverity(ToastSeverity severity) {
        this.severity = severity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getScript(){
        return String.format("toarstMessage('%s','%s','%s');", getSeverity().name(), getMessage(), getTitle());
    }
}
