package com.bakuard.lessons.exceptions;

public class UnknownScope extends RuntimeException {

    public UnknownScope() {
    }

    public UnknownScope(String message) {
        super(message);
    }

    public UnknownScope(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownScope(Throwable cause) {
        super(cause);
    }

}
