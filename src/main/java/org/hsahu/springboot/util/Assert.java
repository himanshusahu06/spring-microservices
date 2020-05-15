package org.hsahu.springboot.util;

/**
 * Object assertion library
 */
public class Assert {
    public static void notNull(Object object, String errorString) throws IllegalArgumentException {
        if (object == null) {
            throw new IllegalArgumentException(errorString);
        }
     }

    public static void notEmpty(String str, String errorString) throws IllegalArgumentException {
        notNull(str, errorString);
        if (str.trim().isEmpty()) {
            throw new IllegalArgumentException(errorString);
        }
    }
}
