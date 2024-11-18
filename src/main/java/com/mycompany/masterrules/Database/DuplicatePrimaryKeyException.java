package com.mycompany.masterrules.Database;

public class DuplicatePrimaryKeyException extends RuntimeException {
    public DuplicatePrimaryKeyException(String message) {
        super(message);
    }
}
