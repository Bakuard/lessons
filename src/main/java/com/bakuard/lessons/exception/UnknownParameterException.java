package com.bakuard.lessons.exception;

public class UnknownParameterException extends RuntimeException {

    public UnknownParameterException() {

    }

    public UnknownParameterException(String message) {
        super(message);
    }

    public UnknownParameterException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownParameterException(Throwable cause) {
        super(cause);
    }

}
