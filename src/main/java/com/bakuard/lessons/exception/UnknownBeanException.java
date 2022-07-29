package com.bakuard.lessons.exception;

public class UnknownBeanException extends RuntimeException {

    public UnknownBeanException() {

    }

    public UnknownBeanException(String message) {
        super(message);
    }

    public UnknownBeanException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownBeanException(Throwable cause) {
        super(cause);
    }

}
