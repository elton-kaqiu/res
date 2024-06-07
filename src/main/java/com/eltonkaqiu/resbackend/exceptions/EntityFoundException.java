package com.eltonkaqiu.resbackend.exceptions;

public class EntityFoundException extends RuntimeException {
    public EntityFoundException() {
    }

    public EntityFoundException(String message) {
        super(message);
    }
}
