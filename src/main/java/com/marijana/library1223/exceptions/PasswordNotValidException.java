package com.marijana.library1223.exceptions;

public class PasswordNotValidException extends RuntimeException {

    public PasswordNotValidException() {
        super();
    }

    public PasswordNotValidException(String message) {
        super(message);

    }
}
