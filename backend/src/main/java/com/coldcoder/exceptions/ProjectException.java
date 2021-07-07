package com.coldcoder.exceptions;

public class ProjectException extends Exception{
    String errorMessage;
    Throwable error;

    public ProjectException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public ProjectException(String errorMessage, Throwable error) {
        super(errorMessage, error);
        this.errorMessage = errorMessage;
        this.error = error;
    }
}
