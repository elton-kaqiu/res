package com.eltonkaqiu.resbackend.exceptions;

public class EntityFoundException extends ApiRuntimeException {
    public EntityFoundException() {
    }

    public EntityFoundException(String message) {
        super(message);
    }
}
