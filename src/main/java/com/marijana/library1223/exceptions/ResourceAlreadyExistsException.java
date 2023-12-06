package com.marijana.library1223.exceptions;

public class ResourceAlreadyExistsException extends RuntimeException {

    public ResourceAlreadyExistsException() {
        super("Inserted resource already exists in the database.");
    }

    public ResourceAlreadyExistsException(String message) {
        super(message);
    }

}
