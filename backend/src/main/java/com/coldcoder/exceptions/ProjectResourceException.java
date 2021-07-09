package com.coldcoder.exceptions;

public class ProjectResourceException extends RuntimeException {
    String errorMessage;
    Throwable error;

    public ProjectResourceException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public ProjectResourceException(String errorMessage, Throwable error) {
        super(errorMessage, error);
        this.errorMessage = errorMessage;
        this.error = error;
    }
}
