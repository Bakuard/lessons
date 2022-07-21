package com.bakuard.lessons.exceptions;

public class UnknownDependencyTypeException extends RuntimeException {

    public UnknownDependencyTypeException() {
    }

    public UnknownDependencyTypeException(String message) {
        super(message);
    }

    public UnknownDependencyTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownDependencyTypeException(Throwable cause) {
        super(cause);
    }

}
