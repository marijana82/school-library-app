package com.marijana.library1223.exceptions;

public class AccessDeniedException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public AccessDeniedException() {
        super();
    }
    public AccessDeniedException(String message) {
        super(message);
    }
}
