package com.marijana.library1223.exceptions;

public class IdNotFoundException extends RuntimeException {

    public IdNotFoundException() {
        super("Id not found");
    }

    public IdNotFoundException(String message) {
        super(message);
    }
}
