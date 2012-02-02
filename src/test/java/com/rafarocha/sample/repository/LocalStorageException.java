package com.rafarocha.sample.repository;

public class LocalStorageException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public LocalStorageException(String message) {
        super(message);
    }
    
    public LocalStorageException(String message, Throwable cause) {
        super(message, cause);
    }
    
}