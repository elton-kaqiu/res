package com.eltonkaqiu.resbackend.infrastructure.exceptions;

public class EntityNotFoundException extends ApiRuntimeException{
    public EntityNotFoundException() {
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}
