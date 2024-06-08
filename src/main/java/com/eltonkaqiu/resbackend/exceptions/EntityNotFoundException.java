package com.eltonkaqiu.resbackend.exceptions;

public class EntityNotFoundException extends ApiRuntimeException{
    public EntityNotFoundException() {
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}
