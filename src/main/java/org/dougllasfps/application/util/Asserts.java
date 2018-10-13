package org.dougllasfps.application.util;

public interface Asserts {

    static boolean invalidString(String string){
        return string == null || "".equals(string.trim());
    }
}
