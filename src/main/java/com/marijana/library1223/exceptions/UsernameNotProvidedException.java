package com.marijana.library1223.exceptions;

public class UsernameNotProvidedException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UsernameNotProvidedException(String username) {
        super("Username is empty, please provide one to continue.");
    }
}
